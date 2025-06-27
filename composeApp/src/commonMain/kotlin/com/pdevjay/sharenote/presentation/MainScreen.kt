package com.pdevjay.sharenote.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pdevjay.sharenote.presentation.appbar.BottomBarData
import com.pdevjay.sharenote.presentation.appbar.CustomBottomAppBar
import com.pdevjay.sharenote.presentation.appbar.CustomTopAppBar
import com.pdevjay.sharenote.presentation.appbar.LocalBottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalTopBarData
import com.pdevjay.sharenote.presentation.appbar.TopBarData
import com.pdevjay.sharenote.presentation.navigation.BottomNavItem
import com.pdevjay.sharenote.presentation.navigation.BottomNavigationBar
import com.pdevjay.sharenote.presentation.navigation.MainTopAppBar
import com.pdevjay.sharenote.presentation.navigation.MyBottomAppBar
import com.pdevjay.sharenote.presentation.navigation.NavGraph
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    var showAddNote by rememberSaveable { mutableStateOf(false) }

    var bottomBarData by remember {
        mutableStateOf(
            BottomBarData(
            )
        )
    }

    var topBarData by remember {
        mutableStateOf(
            TopBarData(
            )
        )
    }
    CompositionLocalProvider(
        LocalBottomBarData provides { bottomBarData = it },
        LocalTopBarData provides { topBarData = it }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AnimatedContent(
                    targetState = topBarData
                ) { data ->
                    if (data.visible) {
                        CustomTopAppBar(
                            modifier = data.modifier,
                            title = data.title,
                            navigationIcon = data.navigationIcon,
                            actions = data.actions
                        )
                    }
                }
            },
            bottomBar = {
                AnimatedContent(
                    targetState = bottomBarData
                ) { data ->
                    if (data.visible) {
                        CustomBottomAppBar(
                            modifier = data.modifier,
                            content = data.content
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavGraph(navController = navController, paddingValues = paddingValues)
        }
    }
}