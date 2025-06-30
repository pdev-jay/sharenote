package com.pdevjay.sharenote.data.repository

import com.pdevjay.sharenote.database.DatabaseDriverFactory
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.repository.NoteRepository

class InMemoryNoteRepository(
    databaseDriverFactory: DatabaseDriverFactory
) : NoteRepository {
    private val notes = mutableListOf<Note>()

    override fun getAllNotes(): List<Note> = notes.toList()

    override fun addNote(note: Note) {
        notes.add(note)
    }

//    override fun deleteNote(note: Note) {
//        notes.remove(note)
//    }
//
//    override fun clear() {
//        notes.clear()
//    }
}