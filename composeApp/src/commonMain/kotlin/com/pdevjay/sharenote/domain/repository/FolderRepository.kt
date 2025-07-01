package com.pdevjay.sharenote.domain.repository

import com.pdevjay.sharenote.domain.model.Folder
import kotlinx.coroutines.flow.Flow


interface FolderRepository {
    fun getAllFolders(): Flow<List<Folder>>

    suspend fun getDefaultFolder(): Folder?

    suspend fun getLastInsertRowId(): Long
    suspend fun addFolder(folder: Folder)

    suspend fun getFolderByName(name: String): Folder?

    suspend fun getFolderById(id: Long): Folder?
}