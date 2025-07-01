package com.pdevjay.sharenote.domain.usecase

import com.pdevjay.sharenote.data.repository.FolderRepositoryImpl
import com.pdevjay.sharenote.data.repository.NoteRepositoryImpl
import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

data class FolderUseCases(
    val getFolders: GetFolders,
    val addFolder: AddFolder,
    val getDefaultFolder: GetDefaultFolder,
    val getLastInsertRowId: GetLastInsertRowId,
    val getFolderByName: GetFolderByName,
    val getFolderById: GetFolderById,
)

class GetFolders(private val repository: FolderRepository) {
    operator fun invoke(): Flow<List<Folder>> = repository.getAllFolders()
}

class GetDefaultFolder(private val repository: FolderRepository) {
    operator suspend fun invoke(): Folder? = repository.getDefaultFolder()
}

class GetLastInsertRowId(private val repository: FolderRepository) {
    operator suspend fun invoke(): Long = repository.getLastInsertRowId()
}

class AddFolder(private val repository: FolderRepository) {
    operator suspend fun invoke(folder: Folder) = repository.addFolder(folder)
}

class GetFolderByName(private val repository: FolderRepository) {
    operator suspend fun invoke(name: String): Folder? = repository.getFolderByName(name)
}

class GetFolderById(private val repository: FolderRepository) {
    operator suspend fun invoke(id: Long): Folder? = repository.getFolderById(id)
}