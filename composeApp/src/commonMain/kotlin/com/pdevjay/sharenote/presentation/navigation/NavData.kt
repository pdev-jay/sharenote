package com.pdevjay.sharenote.presentation.navigation

import com.pdevjay.sharenote.domain.model.Note
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class AddNote(val folderId: Long? = null)

@Serializable
data class NoteDetail(val noteId: Long)