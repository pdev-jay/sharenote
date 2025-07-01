package com.pdevjay.sharenote.presentation.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pdevjay.sharenote.presentation.appbar.BottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalBottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalTopBarData
import com.pdevjay.sharenote.presentation.appbar.TopBarData
import com.pdevjay.sharenote.presentation.util.toYyyyMmDd
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteDetailScreen(noteId: Long, onPopBackStack: () -> Unit = {}, onClickEditButton: (Long) -> Unit = {})  {
    val noteDetailViewModel = koinViewModel<NoteDetailViewModel>(){ parametersOf(noteId)}
    val note by noteDetailViewModel.selectedNote.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current

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
                actions = {
                    TextButton(
                        onClick = {
                            onClickEditButton(note?.id ?: -1)
                        }
                    ){
                        Text("Edit")
                    }
                }
            ),
        )

        setBottomBar(
            BottomBarData(
                visible = false,
            )
        )
    }

    LifecycleResumeEffect(key1 = lifecycleOwner) {
        noteDetailViewModel.loadNote()
        onPauseOrDispose {
        }
    }


        AnimatedVisibility(visible = note != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

            Text("${note?.title}", style = MaterialTheme.typography.headlineMedium)
            Text("${note?.createdAt?.toYyyyMmDd()}", style = MaterialTheme.typography.labelMedium)
            Text("${note?.body}")
        }
    }
}