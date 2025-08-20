package com.compose.notes.application.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.compose.notes.application.feature_notes.data.data_source.NoteDataBase
import com.compose.notes.application.feature_notes.data.repository.NoteRepository
import com.compose.notes.application.feature_notes.data.repository.NoteRepositoryImplementation
import com.compose.notes.application.feature_notes.domain.usecaase.AddNoteUseCase
import com.compose.notes.application.feature_notes.domain.usecaase.DeleteNoteUseCase
import com.compose.notes.application.feature_notes.domain.usecaase.GetNotesUseCase
import com.compose.notes.application.feature_notes.domain.usecaase.NoteUseCases
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module{

    single {
        Room.databaseBuilder(androidApplication(),
            NoteDataBase::class.java,
            NoteDataBase.DATABASE_NAME
        ).build()
    }

    single { get<NoteDataBase>().noteDao }

    single<NoteRepository> { NoteRepositoryImplementation(get()) }

    // UseCases
    single { GetNotesUseCase(get()) }
    single { AddNoteUseCase(get()) }
    single { DeleteNoteUseCase(get()) }

    // Grouped UseCases
    single {
        NoteUseCases(
            getNotesUseCase = get(),
            addNoteUseCase = get (),
            deleteNoteUseCase = get()
        )
    }
}