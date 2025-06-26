package com.pdevjay.sharenote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform