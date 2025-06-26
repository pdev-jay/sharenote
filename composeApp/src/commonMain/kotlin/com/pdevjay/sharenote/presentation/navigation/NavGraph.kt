package com.pdevjay.sharenote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdevjay.sharenote.presentation.HomeScreen
import com.pdevjay.sharenote.presentation.TestScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }

        composable(route = "test") {
            TestScreen()
        }
    }
}