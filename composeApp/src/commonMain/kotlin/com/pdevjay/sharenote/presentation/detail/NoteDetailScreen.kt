package com.pdevjay.sharenote.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdevjay.sharenote.presentation.appbar.BottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalBottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalTopBarData
import com.pdevjay.sharenote.presentation.appbar.TopBarData
import com.pdevjay.sharenote.presentation.util.toYyyyMmDd
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteDetailScreen(noteId: Long, onPopBackStack: () -> Unit = {})  {
    val noteDetailViewModel = koinViewModel<NoteDetailViewModel>(){ parametersOf(noteId)}
    val note by noteDetailViewModel.selectedNote.collectAsState()

    val setTopBar = LocalTopBarData.current
    val setBottomBar = LocalBottomBarData.current

    LaunchedEffect(Unit) {
        setTopBar(
            TopBarData(
                title = "Detail",
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onPopBackStack()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            ),
        )

        setBottomBar(
            BottomBarData(
                visible = false,
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text("${note?.title}", style = MaterialTheme.typography.headlineMedium)
        Text("${note?.createdAt?.toYyyyMmDd()}", style = MaterialTheme.typography.labelMedium)
        Text("${note?.body}")
    }
}