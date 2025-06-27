package com.pdevjay.sharenote.di

import com.pdevjay.sharenote.domain.repository.NoteRepository
import com.pdevjay.sharenote.data.repository.InMemoryNoteRepository
import com.pdevjay.sharenote.domain.usecase.AddNote
import com.pdevjay.sharenote.domain.usecase.ClearNotes
import com.pdevjay.sharenote.domain.usecase.DeleteNote
import com.pdevjay.sharenote.domain.usecase.GetNotes
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import com.pdevjay.sharenote.presentation.add.AddViewModel
import com.pdevjay.sharenote.presentation.home.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val koinModule = module {
    // Repository (메모리 저장소)
    single<NoteRepository> { InMemoryNoteRepository() }

    // UseCases
    factory { GetNotes(get()) }
    factory { AddNote(get()) }
    factory { DeleteNote(get()) }
    factory { ClearNotes(get()) }

    // UseCases 묶음
    single {
        NoteUseCases(
            getNotes = get(),
            addNote = get(),
            deleteNote = get(),
            clearNotes = get()
        )
    }

    viewModelOf(::HomeViewModel)
    viewModelOf(::AddViewModel)

}

fun initKoin() {
    startKoin {
        modules(
            koinModule
        )
    }
}