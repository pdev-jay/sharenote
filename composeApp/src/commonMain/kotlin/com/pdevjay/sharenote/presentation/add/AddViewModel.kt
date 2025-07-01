package com.pdevjay.sharenote.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class AddViewModel(
    private val useCases: NoteUseCases
): ViewModel() {
    fun addNote(folderId: Long, title: String, body: String) {
        viewModelScope.launch {
            val newNote = Note(folderId = folderId, title = title, body = body, createdAt = Clock.System.now())
            useCases.addNote(newNote)
        }
    }
}