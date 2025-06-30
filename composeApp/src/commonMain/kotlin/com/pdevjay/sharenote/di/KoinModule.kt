package com.pdevjay.sharenote.di

import com.pdevjay.sharenote.data.local.DatabaseHelper
import com.pdevjay.sharenote.data.repository.NoteRepositoryImpl
import com.pdevjay.sharenote.database.DatabaseDriverFactory
import com.pdevjay.sharenote.domain.usecase.AddNote
import com.pdevjay.sharenote.domain.usecase.GetNotes
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import com.pdevjay.sharenote.presentation.add.AddViewModel
import com.pdevjay.sharenote.presentation.home.HomeViewModel
import com.pdevjay.sharenote.Database
import com.pdevjay.sharenote.data.repository.FolderRepositoryImpl
import com.pdevjay.sharenote.domain.repository.FolderRepository
import com.pdevjay.sharenote.domain.repository.NoteRepository
import com.pdevjay.sharenote.domain.usecase.AddFolder
import com.pdevjay.sharenote.domain.usecase.FolderUseCases
import com.pdevjay.sharenote.domain.usecase.GetDefaultFolder
import com.pdevjay.sharenote.domain.usecase.GetFolderById
import com.pdevjay.sharenote.domain.usecase.GetFolderByName
import com.pdevjay.sharenote.domain.usecase.GetFolders
import com.pdevjay.sharenote.domain.usecase.GetLastInsertRowId
import com.pdevjay.sharenote.domain.usecase.GetNotesInFolder
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val databaseModule = module {
    single { get<DatabaseDriverFactory>().createDriver() }
    single { Database(get()) } // Database 인스턴스를 Koin에 싱글톤으로 등록
    single { DatabaseHelper(get<Database>()) } // DatabaseHelper는 Database 인스턴스를 직접 주입받음
}
val koinModule = module {

    single<NoteRepository> { NoteRepositoryImpl(get()) }
    single<FolderRepository> { FolderRepositoryImpl(get()) }

    // UseCases
    factory { GetNotes(get()) }
    factory { AddNote(get()) }
    factory { GetNotesInFolder(get()) }
//    factory { DeleteNote(get()) }
//    factory { ClearNotes(get()) }

    factory { GetFolders(get()) }
    factory { AddFolder(get()) }
    factory { GetDefaultFolder(get()) }
    factory { GetLastInsertRowId(get()) }
    factory { GetFolderByName(get()) }
    factory { GetFolderById(get()) }
    // UseCases 묶음
    single {
        NoteUseCases(
            getNotes = get(),
            addNote = get(),
            getNotesInFolder = get(),
//            deleteNote = get(),
//            clearNotes = get()
        )
    }

    single {
        FolderUseCases(
            getFolders = get(),
            addFolder = get(),
            getDefaultFolder = get(),
            getLastInsertRowId = get(),
            getFolderByName = get(),
            getFolderById = get()
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