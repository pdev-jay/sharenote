package com.pdevjay.sharenote.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Test : BottomNavItem("test", Icons.Default.Search, "Test")
    companion object {
        val items: List<BottomNavItem>
            get() = listOf(Home, Test) // 매번 가져올 때 강제 초기화됨
    }
}