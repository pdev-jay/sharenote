package com.pdevjay.sharenote.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import kotlinx.coroutines.launch

class NoteEditViewModel(
    private val noteId: Long = -1L,
    private val useCases: NoteUseCases
): ViewModel() {

    val selectedNote = useCases.getNoteById(noteId)

    fun updateNote(newNote: Note) {
        viewModelScope.launch {
            useCases.updateNote(newNote)
        }
    }
}