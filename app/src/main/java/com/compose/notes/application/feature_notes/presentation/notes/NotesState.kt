package com.compose.notes.application.feature_notes.presentation.notes

import com.compose.notes.application.feature_notes.domain.model.Note
import com.compose.notes.application.feature_notes.domain.util.NoteOrder
import com.compose.notes.application.feature_notes.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSecVisible: Boolean = false,
)
