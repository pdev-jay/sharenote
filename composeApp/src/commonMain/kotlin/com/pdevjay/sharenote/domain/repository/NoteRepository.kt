package com.pdevjay.sharenote.domain.repository

import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.model.Note

interface NoteRepository {
    fun getAllNotes(): List<Note>

    fun getAllNotesInFolder(folderId: Long): List<Note>
    fun addNote(note: Note)
    fun deleteNote(note: Note)
//    fun clear()
}