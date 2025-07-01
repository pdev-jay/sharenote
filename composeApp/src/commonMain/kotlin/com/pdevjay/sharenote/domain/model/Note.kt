package com.pdevjay.sharenote.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Note constructor(
    val id: Long? = null,
    val folderId: Long? = null, // 추가
    val title: String = "",
    val body: String = "",
    val createdAt: Instant = Clock.System.now()
)