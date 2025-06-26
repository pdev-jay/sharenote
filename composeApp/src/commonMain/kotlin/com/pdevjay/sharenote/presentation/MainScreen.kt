package com.pdevjay.sharenote.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pdevjay.sharenote.presentation.navigation.BottomNavItem
import com.pdevjay.sharenote.presentation.navigation.BottomNavigationBar
import com.pdevjay.sharenote.presentation.navigation.NavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute ?: BottomNavItem.Home.route,
                onItemClick = { navController.navigate(it.route) }
            )
        }
    ) { paddingValues ->
        NavGraph(navController = navController)
    }
}