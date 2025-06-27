package com.pdevjay.sharenote.domain.repository

import com.pdevjay.sharenote.domain.model.Note

interface NoteRepository {
    fun getAllNotes(): List<Note>
    fun addNote(note: Note)
    fun deleteNote(note: Note)
    fun clear()
}