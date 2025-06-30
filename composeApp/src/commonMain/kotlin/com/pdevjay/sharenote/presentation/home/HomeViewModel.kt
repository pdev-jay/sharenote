package com.pdevjay.sharenote.presentation.home

import androidx.lifecycle.ViewModel
import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.usecase.FolderUseCases
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import com.pdevjay.sharenote.util.AppPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val prefs: AppPreferences,
    private val noteUseCases: NoteUseCases,
    private val folderUseCases: FolderUseCases
): ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes
    
    private val _selectedNote = MutableStateFlow<Note?>(null)
    val selectedNote: StateFlow<Note?> = _selectedNote
    
    private val _folders = MutableStateFlow<List<Folder>>(emptyList())
    val folders: StateFlow<List<Folder>> = _folders

    private val _selectedFolder = MutableStateFlow<Folder?>(null)
    val selectedFolder: StateFlow<Folder?> = _selectedFolder


    init {
        val defaultFolder = getOrCreateDefaultFolder()
        _selectedFolder.value = defaultFolder
        prefs.setFirstLaunchDone()
    }
    fun setSelectedFolder(folder: Folder?) {
        _selectedFolder.value = folder
        loadNotes()
    }
    
    fun setSelectedNote(note: Note?) {
        _selectedNote.value = note
    }

    fun loadNotes() {
//        _notes.value = noteUseCases.getNotes()
        _notes.value = noteUseCases.getNotesInFolder(_selectedFolder.value?.id ?: -1)
    }
    
    fun loadFolders() {
        _folders.value = folderUseCases.getFolders()
    }

    fun getDefaultFolder() = folderUseCases.getDefaultFolder()
    fun addFolder(folder: Folder): Long {
        folderUseCases.addFolder(folder)
        _folders.value = folderUseCases.getFolders()

        return folderUseCases.getLastInsertRowId()
    }

    fun getOrCreateDefaultFolder(): Folder {
        val default = folderUseCases.getFolderByName("Default")
        return if (default != null) {
            default
        } else {
            val lastInsertRowId = addFolder(Folder(name = "Default"))
            folderUseCases.getFolderById(lastInsertRowId) ?: Folder(id = lastInsertRowId, name = "Default")
        }
    }

}