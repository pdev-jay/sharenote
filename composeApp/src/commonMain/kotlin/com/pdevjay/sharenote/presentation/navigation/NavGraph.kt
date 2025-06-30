package com.pdevjay.sharenote.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.pdevjay.sharenote.presentation.add.AddNoteScreen
import com.pdevjay.sharenote.presentation.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues,
) {

    NavHost(navController = navController, modifier = Modifier.padding(paddingValues).padding(8.dp), startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onNavigateToAddNote = { folderId: Long ->
                    navController.navigate(AddNote(folderId)) // 여기에 folderId 값 삽입
                }
            )
        }

        composable<AddNote>(
        ) {backStackEntry->
            val addNode = backStackEntry.toRoute<AddNote>()
            AddNoteScreen(
                folderId = addNode.folderId ?: -1L,
                onPopBackStack = { navController.popBackStack() }
            )
        }
    }
}