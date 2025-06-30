package com.pdevjay.sharenote.data.local

import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.Database
import kotlinx.datetime.Instant

class DatabaseHelper(database: Database) {
    private val dbQuery = database.noteQueries

    fun selectAllNotes(): List<Note> = dbQuery.selectAll().executeAsList().map{ it.toDomain()}

    fun insertNote(note: Note) = dbQuery.insertNote(title = note.title, body = note.body, createdAt = note.createdAt.toEpochMilliseconds())
}

// SQLDelight Note -> 내 도메인 Note 로 매핑
fun com.pdevjay.sharenote.Note.toDomain(): com.pdevjay.sharenote.domain.model.Note {
    return com.pdevjay.sharenote.domain.model.Note(
        id = this.id,
        title = this.title,
        body = this.body,
        createdAt = Instant.fromEpochMilliseconds(this.createdAt)
    )
}