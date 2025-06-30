package com.pdevjay.sharenote.data.repository

import com.pdevjay.sharenote.data.local.DatabaseHelper
import com.pdevjay.sharenote.domain.model.Folder
import com.pdevjay.sharenote.domain.repository.FolderRepository

class FolderRepositoryImpl(
    private val db: DatabaseHelper

): FolderRepository {
    override fun getAllFolders(): List<Folder> {
        return db.selectAllFolders()
    }

    override fun getDefaultFolder(): Folder? {
        return db.selectDefaultFolder()
    }

    override fun getLastInsertRowId(): Long {
        return db.selectLastInsertRowId()
    }
    override fun addFolder(folder: Folder) {
        db.insertFolder(folder)
    }

    override fun getFolderByName(name: String): Folder? {
        return db.selectFolderByName(name)
    }

    override fun getFolderById(id: Long): Folder? {
        return db.selectFolderById(id)
    }

}