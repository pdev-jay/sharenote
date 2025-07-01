package com.pdevjay.sharenote.domain.usecase

import com.pdevjay.sharenote.data.repository.NoteRepositoryImpl
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.repository.NoteRepository

data class NoteUseCases(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val getNotesInFolder: GetNotesInFolder,
    val getNoteById: GetNoteById,
//    val deleteNote: DeleteNote,
//    val clearNotes: ClearNotes
)

class GetNotes(private val repository: NoteRepository) {
    operator fun invoke(): List<Note> = repository.getAllNotes()
}

class GetNoteById(private val repository: NoteRepository) {
    operator fun invoke(noteId: Long): Note? = repository.getNoteById(noteId)
}
class GetNotesInFolder(private val repository: NoteRepository) {
    operator fun invoke(folderId: Long): List<Note> = repository.getAllNotesInFolder(folderId)
}

class AddNote(private val repository: NoteRepository) {
    operator fun invoke(note: Note) = repository.addNote(note)
}

class DeleteNote(private val repository: NoteRepository) {
    operator fun invoke(note: Note) = repository.deleteNote(note)
}
//
//class ClearNotes(private val repository: NoteRepository) {
//    operator fun invoke() = repository.clear()
//}