package com.compose.notes.application.feature_notes.data.repository

import com.compose.notes.application.feature_notes.data.data_source.NoteDao
import com.compose.notes.application.feature_notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImplementation(
    private val noteDao: NoteDao
): NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note)
    }
}