package com.pdevjay.sharenote.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar {
        println("Home = ${BottomNavItem.Home}")
        println("Test = ${BottomNavItem.Test}")
        println("items = ${BottomNavItem.items}")
        BottomNavItem.items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = item.route == currentRoute,
                onClick = { onItemClick(item) }
            )
        }
    }
}