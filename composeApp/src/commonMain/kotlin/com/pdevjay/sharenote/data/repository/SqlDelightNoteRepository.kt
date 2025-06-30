package com.pdevjay.sharenote.data.repository

import com.pdevjay.sharenote.data.local.DatabaseHelper
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.repository.NoteRepository

class SqlDelightNoteRepository(
    private val db: DatabaseHelper
) : NoteRepository {

    override fun getAllNotes(): List<Note> = db.selectAllNotes()

    override fun addNote(note: Note) {
        db.insertNote(note)
    }

}