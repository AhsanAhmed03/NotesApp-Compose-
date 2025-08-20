package com.compose.notes.application.feature_notes.presentation.notes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.notes.application.feature_notes.domain.model.Note
import com.compose.notes.application.feature_notes.domain.usecaase.GetNotesUseCase
import com.compose.notes.application.feature_notes.domain.usecaase.NoteUseCases
import com.compose.notes.application.feature_notes.domain.util.NoteOrder
import com.compose.notes.application.feature_notes.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NotesViewModel (private val notesUseCase: NoteUseCases): ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state : State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNote(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                if (state.value.notes::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType) {
                    return
                }
                getNote(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCase.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCase.addNoteUseCase(recentlyDeletedNote ?: return@launch )
                    recentlyDeletedNote = null
                }
            }

            is NotesEvent.ToggleOrderSelection -> {
                _state.value = state.value.copy(
                    isOrderSecVisible = !state.value.isOrderSecVisible
                )
            }


        }
    }

    private fun getNote(noteOrder: NoteOrder) {

        getNotesJob?.cancel()
        getNotesJob = notesUseCase.getNotesUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}