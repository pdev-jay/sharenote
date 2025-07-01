package com.pdevjay.sharenote.data.repository

import com.pdevjay.sharenote.data.local.DatabaseHelper
import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class FolderRepositoryImpl(
    private val db: DatabaseHelper

): FolderRepository {
    override fun getAllFolders(): Flow<List<Folder>> {
        return db.selectAllFolders()
    }

    override suspend fun getDefaultFolder(): Folder? {
        return db.selectDefaultFolder()
    }

    override suspend fun getLastInsertRowId(): Long {
        return db.selectLastInsertRowId()
    }
    override suspend fun addFolder(folder: Folder) {
        db.insertFolder(folder)
    }

    override suspend fun getFolderByName(name: String): Folder? {
        return db.selectFolderByName(name)
    }

    override suspend fun getFolderById(id: Long): Folder? {
        return db.selectFolderById(id)
    }

}