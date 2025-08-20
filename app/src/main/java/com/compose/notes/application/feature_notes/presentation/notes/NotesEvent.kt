package com.compose.notes.application.feature_notes.presentation.notes

import com.compose.notes.application.feature_notes.domain.model.Note
import com.compose.notes.application.feature_notes.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSelection: NotesEvent()
}