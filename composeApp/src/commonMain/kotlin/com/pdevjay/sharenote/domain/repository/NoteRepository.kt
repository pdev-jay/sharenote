package com.pdevjay.sharenote.domain.repository

import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    fun getNoteById(id: Long): Flow<Note?>
    fun getAllNotesInFolder(folderId: Long): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
//    fun clear()
}