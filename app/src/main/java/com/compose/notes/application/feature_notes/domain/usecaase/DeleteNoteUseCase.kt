package com.compose.notes.application.feature_notes.domain.usecaase

import com.compose.notes.application.feature_notes.data.repository.NoteRepository
import com.compose.notes.application.feature_notes.domain.model.Note

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note){
        noteRepository.deleteNote(note)
    }
}