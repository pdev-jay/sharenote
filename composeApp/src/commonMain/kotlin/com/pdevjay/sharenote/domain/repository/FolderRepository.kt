package com.pdevjay.sharenote.domain.repository

import com.pdevjay.sharenote.domain.model.Folder


interface FolderRepository {
    fun getAllFolders(): List<Folder>

    fun getDefaultFolder(): Folder?

    fun getLastInsertRowId(): Long
    fun addFolder(folder: Folder)

    fun getFolderByName(name: String): Folder?

    fun getFolderById(id: Long): Folder?
}