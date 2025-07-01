package com.pdevjay.sharenote.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.usecase.FolderUseCases
import com.pdevjay.sharenote.domain.usecase.NoteUseCases
import com.pdevjay.sharenote.util.AppPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val prefs: AppPreferences,
    private val noteUseCases: NoteUseCases,
    private val folderUseCases: FolderUseCases
): ViewModel() {
    private val _selectedFolder = MutableStateFlow<Folder?>(null)
    val selectedFolder: StateFlow<Folder?> = _selectedFolder

    val folders: Flow<List<Folder>> = folderUseCases.getFolders()
    @OptIn(ExperimentalCoroutinesApi::class)
    val notes: Flow<List<Note>> = _selectedFolder
        .flatMapLatest { folder ->
            noteUseCases.getNotesInFolder(folder?.id?:-1)
        }

    init {
        ensureDefaultFolderExists()
    }
    fun setSelectedFolder(folder: Folder?) {
        _selectedFolder.value = folder
    }


    fun addFolder(folder: Folder) {
        viewModelScope.launch {

            folderUseCases.addFolder(folder)
        }
    }

    private fun ensureDefaultFolderExists() {
        viewModelScope.launch {
            val default = folderUseCases.getFolderByName("Default")
            val folder = if (default != null) {
                default
            } else {
                folderUseCases.addFolder(Folder(name = "Default"))
                val id = folderUseCases.getLastInsertRowId()
                folderUseCases.getFolderById(id) ?: Folder(id = id, name = "Default")
            }
            _selectedFolder.value = folder
            prefs.setFirstLaunchDone()
        }
    }

}