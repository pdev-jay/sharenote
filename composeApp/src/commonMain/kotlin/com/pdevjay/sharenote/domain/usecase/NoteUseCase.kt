package com.pdevjay.sharenote.domain.usecase

import com.pdevjay.sharenote.data.repository.NoteRepositoryImpl
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

data class NoteUseCases(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val getNotesInFolder: GetNotesInFolder,
    val getNoteById: GetNoteById,
    val updateNote: UpdateNote,
//    val deleteNote: DeleteNote,
//    val clearNotes: ClearNotes
)

class GetNotes(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> = repository.getAllNotes()
}

class GetNoteById(private val repository: NoteRepository) {
    operator fun invoke(noteId: Long): Flow<Note?> = repository.getNoteById(noteId)
}
class GetNotesInFolder(private val repository: NoteRepository) {
    operator fun invoke(folderId: Long): Flow<List<Note>> = repository.getAllNotesInFolder(folderId)
}

class AddNote(private val repository: NoteRepository) {
    operator suspend fun invoke(note: Note) = repository.addNote(note)
}

class DeleteNote(private val repository: NoteRepository) {
    operator suspend fun invoke(note: Note) = repository.deleteNote(note)
}

class UpdateNote(private val repository: NoteRepository) {
    operator suspend fun invoke(note: Note) = repository.updateNote(note)
}
//
//class ClearNotes(private val repository: NoteRepository) {
//    operator fun invoke() = repository.clear()
//}