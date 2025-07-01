package com.pdevjay.sharenote.data.local

import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.Database
import com.pdevjay.sharenote.domain.model.Folder
import kotlinx.datetime.Instant

class DatabaseHelper(database: Database) {
    private val noteQueries = database.noteQueries
    private val folderQueries = database.folderQueries

    fun selectAllNotes(): List<Note> = noteQueries.selectAll().executeAsList().map{ it.toDomain()}
    fun selectNoteById(id: Long): Note? = noteQueries.selectNoteById(id).executeAsOneOrNull()?.toDomain()
    fun selectNotesByFolderId(folderId: Long): List<Note> = noteQueries.selectNotesByFolderId(folderId).executeAsList().map{ it.toDomain()}
    fun insertNote(note: Note) = noteQueries.insertNote(folderId = note.folderId, title = note.title, body = note.body, createdAt = note.createdAt.toEpochMilliseconds())

    fun deleteNote(note: Note) = noteQueries.deleteNoteById(note.id ?: 0)

    fun selectAllFolders(): List<Folder> = folderQueries.selectAll().executeAsList().map{ it.toDomain()}
    fun selectDefaultFolder(): Folder? = folderQueries.selectDefaultFolder().executeAsOneOrNull()?.toDomain()
    fun selectLastInsertRowId(): Long = folderQueries.lastInsertRowId().executeAsOne()
    fun selectFolderByName(name: String): Folder? = folderQueries.selectFolderByName(name).executeAsOneOrNull()?.toDomain()
    fun selectFolderById(id: Long): Folder? = folderQueries.selectFolderById(id).executeAsOneOrNull()?.toDomain()
    fun insertFolder(folder: Folder) = folderQueries.insertFolder(name = folder.name)
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