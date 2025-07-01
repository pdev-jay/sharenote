package com.pdevjay.sharenote.presentation.edit

import androidx.lifecycle.ViewModel
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteEditViewModel(
    private val noteId: Long = -1L,
    private val useCases: NoteUseCases
): ViewModel() {
    private val _selectedNote = MutableStateFlow<Note?>(null)
    val selectedNote: StateFlow<Note?> = _selectedNote

    init {
        loadNote()
    }
    fun loadNote() {
        _selectedNote.value = useCases.getNoteById(noteId)
    }

    fun updateNote(newNote: Note) {
        useCases.updateNote(newNote)
    }
}