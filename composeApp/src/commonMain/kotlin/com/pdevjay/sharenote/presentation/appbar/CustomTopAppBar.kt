package com.pdevjay.sharenote.presentation.appbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
        TopAppBar(
            title = { Text(text = title) },
            modifier = modifier,
            navigationIcon = {
                navigationIcon?.invoke()
            },
            actions = actions
        )
}

val LocalTopBarData = compositionLocalOf<(TopBarData) -> Unit>{
    error("No TopBar setter provided")
}

data class TopBarData(
    val modifier: Modifier = Modifier,
    val title: String = "",
    val navigationIcon: (@Composable () -> Unit)? = null,
    val actions: @Composable RowScope.() -> Unit = {},
    val visible: Boolean = true
)