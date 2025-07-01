package com.pdevjay.sharenote.data.repository

import com.pdevjay.sharenote.data.local.DatabaseHelper
import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val db: DatabaseHelper
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> = db.selectAllNotes()
    override fun getNoteById(id: Long): Flow<Note?> = db.selectNoteById(id)

    override fun getAllNotesInFolder(folderId: Long): Flow<List<Note>> {
        return db.selectNotesByFolderId(folderId)
    }
    override suspend fun addNote(note: Note) {
        db.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        db.deleteNote(note)
    }

    override suspend fun updateNote(note: Note) {
        db.updateNote(note)
    }

}