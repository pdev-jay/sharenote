package com.pdevjay.sharenote.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Note constructor(
    val id: Long? = null,
    val title: String = "",
    val body: String = "",
    val createdAt: Instant = Clock.System.now()
)