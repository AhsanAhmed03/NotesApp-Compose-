package com.compose.notes.application.feature_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.compose.notes.application.ui.theme.BabyBlue
import com.compose.notes.application.ui.theme.LightGray
import com.compose.notes.application.ui.theme.LightGreen
import com.compose.notes.application.ui.theme.RedOrange
import com.compose.notes.application.ui.theme.RedPink

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id : Int? = null
){
    companion object{
        val noteColors = listOf(RedOrange, LightGray, LightGreen, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)
