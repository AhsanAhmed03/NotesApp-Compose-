package com.compose.notes.application.feature_notes.domain.usecaase

import com.compose.notes.application.feature_notes.data.repository.NoteRepository
import com.compose.notes.application.feature_notes.domain.model.InvalidNoteException
import com.compose.notes.application.feature_notes.domain.model.Note


class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
            throw InvalidNoteException("The title to note is empty")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("The content to note is empty")
        }

        repository.insertNote(note)
    }
}