package com.pdevjay.sharenote.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class AddNote(val folderId: Long? = null)