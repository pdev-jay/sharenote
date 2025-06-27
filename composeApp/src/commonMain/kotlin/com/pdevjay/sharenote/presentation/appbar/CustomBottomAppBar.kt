package com.pdevjay.sharenote.presentation.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit = {}
) {
    BottomAppBar(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp),
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = content
            )
        }
    )
}


val LocalBottomBarData = compositionLocalOf <(BottomBarData) -> Unit>  {
    error("No BottomBar setter provided")
}

data class BottomBarData(
    val modifier: Modifier = Modifier,
    val content: @Composable RowScope.() -> Unit = {},
    val visible: Boolean = true,
)