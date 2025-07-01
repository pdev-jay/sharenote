package com.pdevjay.sharenote.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.Database
import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.util.IODispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant

class DatabaseHelper(database: Database) {
    private val noteQueries = database.noteQueries
    private val folderQueries = database.folderQueries

    fun selectAllNotes(): Flow<List<Note>> = noteQueries.selectAll().asFlow().mapToList(IODispatcher).map { notes -> notes.map { it.toDomain()} }
    fun selectNoteById(id: Long): Flow<Note?> = noteQueries.selectNoteById(id).asFlow().mapToOne(IODispatcher).map{it.toDomain()}
    fun selectNotesByFolderId(folderId: Long): Flow<List<Note>> = noteQueries.selectNotesByFolderId(folderId).asFlow().mapToList(IODispatcher).map { notes -> notes.map { it.toDomain()} }
    suspend fun insertNote(note: Note) = withContext(IODispatcher){noteQueries.insertNote(folderId = note.folderId, title = note.title, body = note.body, createdAt = note.createdAt.toEpochMilliseconds())}
    suspend fun updateNote(note: Note) = withContext(IODispatcher){noteQueries.updateNoteById(title = note.title, body = note.body, id = note.id ?: 0)}

    suspend fun deleteNote(note: Note) = withContext(IODispatcher){noteQueries.deleteNoteById(note.id ?: 0)}

    fun selectAllFolders(): Flow<List<Folder>> = folderQueries.selectAll().asFlow().mapToList(IODispatcher).map{ folders -> folders.map{ it.toDomain()}}
    suspend fun selectDefaultFolder(): Folder? = withContext(IODispatcher){folderQueries.selectDefaultFolder().executeAsOneOrNull()?.toDomain()}
    suspend fun selectLastInsertRowId(): Long = withContext(IODispatcher){folderQueries.lastInsertRowId().executeAsOne()}
    suspend fun selectFolderByName(name: String): Folder? = withContext(IODispatcher){folderQueries.selectFolderByName(name).executeAsOneOrNull()?.toDomain()}
    suspend fun selectFolderById(id: Long): Folder? = withContext(IODispatcher){folderQueries.selectFolderById(id).executeAsOneOrNull()?.toDomain()}
    suspend fun insertFolder(folder: Folder) = withContext(IODispatcher){folderQueries.insertFolder(name = folder.name)}
}

// SQLDelight Note -> 내 도메인 Note 로 매핑
fun com.pdevjay.sharenote.Note.toDomain(): com.pdevjay.sharenote.domain.model.Note {
    return com.pdevjay.sharenote.domain.model.Note(
        id = this.id,
        folderId = this.folderId,
        title = this.title,
        body = this.body,
        createdAt = Instant.fromEpochMilliseconds(this.createdAt)
    )
}

fun com.pdevjay.sharenote.Folder.toDomain(): com.pdevjay.sharenote.domain.model.Folder {
    return com.pdevjay.sharenote.domain.model.Folder(
        id = this.id,
        name = this.name
    )
}