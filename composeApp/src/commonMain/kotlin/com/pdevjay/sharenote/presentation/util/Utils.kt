package com.pdevjay.sharenote.presentation.util

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Instant.toYyyyMmDd(): String {
    val localDate = this.toLocalDateTime(TimeZone.currentSystemDefault()).date
    return "${localDate.year}-${localDate.monthNumber.toString().padStart(2, '0')}-${localDate.dayOfMonth.toString().padStart(2, '0')}"
}