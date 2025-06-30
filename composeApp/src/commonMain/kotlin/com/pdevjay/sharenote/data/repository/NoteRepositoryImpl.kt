package com.pdevjay.sharenote.data.repository

import com.pdevjay.sharenote.data.local.DatabaseHelper
import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val db: DatabaseHelper
) : NoteRepository {

    override fun getAllNotes(): List<Note> = db.selectAllNotes()

    override fun getAllNotesInFolder(folderId: Long): List<Note> {
        return db.selectNotesByFolderId(folderId)
    }
    override fun addNote(note: Note) {
        db.insertNote(note)
    }

    override fun deleteNote(note: Note) {
        db.deleteNote(note)
    }

}