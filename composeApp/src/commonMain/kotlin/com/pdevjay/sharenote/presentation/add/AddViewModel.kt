package com.pdevjay.sharenote.presentation.add

import androidx.lifecycle.ViewModel
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import kotlinx.datetime.Clock

class AddViewModel(
    private val useCases: NoteUseCases
): ViewModel() {
    fun addNote(title: String, body: String) {
        val newNote = Note(title, body, Clock.System.now())
        useCases.addNote(newNote)
    }
}