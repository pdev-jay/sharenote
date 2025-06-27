package com.pdevjay.sharenote.domain.usecase

import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.repository.NoteRepository

data class NoteUseCases(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val clearNotes: ClearNotes
)

class GetNotes(private val repository: NoteRepository) {
    operator fun invoke(): List<Note> = repository.getAllNotes()
}

class AddNote(private val repository: NoteRepository) {
    operator fun invoke(note: Note) = repository.addNote(note)
}

class DeleteNote(private val repository: NoteRepository) {
    operator fun invoke(note: Note) = repository.deleteNote(note)
}

class ClearNotes(private val repository: NoteRepository) {
    operator fun invoke() = repository.clear()
}