package com.pdevjay.sharenote.presentation.home

import androidx.lifecycle.ViewModel
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val useCases: NoteUseCases
): ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes
    
    private val _selectedNote = MutableStateFlow<Note?>(null)
    val selectedNote: StateFlow<Note?> = _selectedNote
    
    private val _folders = MutableStateFlow<List<String>>(emptyList())
    val folders: StateFlow<List<String>> = _folders
    
    fun setSelectedNote(note: Note?) {
        _selectedNote.value = note
    }

    fun loadNotes() {
        _notes.value = useCases.getNotes()
        println("Notes : ${_notes.value}")
    }
    
    fun addFolder(folderName: String){
        _folders.value = _folders.value + folderName
    }
}