package com.pdevjay.sharenote.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdevjay.sharenote.presentation.add.AddNoteScreen
import com.pdevjay.sharenote.presentation.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues,
) {

    NavHost(navController = navController, modifier = Modifier.padding(paddingValues).padding(8.dp), startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToAddNote = { navController.navigate("add_note") }
            )
        }

        composable(route = "add_note") {
            AddNoteScreen(onPopBackStack = { navController.popBackStack() })
        }
    }
}