package com.pdevjay.sharenote.di

import com.pdevjay.sharenote.data.local.DatabaseHelper
import com.pdevjay.sharenote.data.repository.InMemoryNoteRepository
import com.pdevjay.sharenote.data.repository.SqlDelightNoteRepository
import com.pdevjay.sharenote.database.DatabaseDriverFactory
import com.pdevjay.sharenote.domain.repository.NoteRepository
import com.pdevjay.sharenote.domain.usecase.AddNote
import com.pdevjay.sharenote.domain.usecase.GetNotes
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import com.pdevjay.sharenote.presentation.add.AddViewModel
import com.pdevjay.sharenote.presentation.home.HomeViewModel
import com.pdevjay.sharenote.Database
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val databaseModule = module {
    single { get<DatabaseDriverFactory>().createDriver() }
    single { Database(get()) } // Database 인스턴스를 Koin에 싱글톤으로 등록
    single { DatabaseHelper(get<Database>()) } // DatabaseHelper는 Database 인스턴스를 직접 주입받음
}
val koinModule = module {

    single<SqlDelightNoteRepository> { SqlDelightNoteRepository(get()) }

    // UseCases
    factory { GetNotes(get()) }
    factory { AddNote(get()) }
//    factory { DeleteNote(get()) }
//    factory { ClearNotes(get()) }

    // UseCases 묶음
    single {
        NoteUseCases(
            getNotes = get(),
            addNote = get(),
//            deleteNote = get(),
//            clearNotes = get()
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