package com.pdevjay.sharenote

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.pdevjay.sharenote.presentation.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainScreen()
    }
}