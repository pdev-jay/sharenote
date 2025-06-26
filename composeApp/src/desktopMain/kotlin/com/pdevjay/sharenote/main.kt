package com.pdevjay.sharenote

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.pdevjay.sharenote.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "sharenote",
    ) {
        App()
    }
}