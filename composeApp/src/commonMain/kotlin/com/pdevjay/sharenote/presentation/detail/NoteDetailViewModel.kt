package com.pdevjay.sharenote.presentation.detail

import androidx.lifecycle.ViewModel
import com.pdevjay.sharenote.domain.usecase.NoteUseCases

class NoteDetailViewModel(
    private val noteId: Long,
    private val useCases: NoteUseCases
): ViewModel() {

    val selectedNote = useCases.getNoteById(noteId)
}