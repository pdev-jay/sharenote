package com.pdevjay.sharenote.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.presentation.appbar.BottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalBottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalTopBarData
import com.pdevjay.sharenote.presentation.appbar.TopBarData
import com.pdevjay.sharenote.presentation.util.toYyyyMmDd
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.ExperimentalTime

@Composable
fun HomeScreen(
    onNavigateToAddNote: () -> Unit = {}
) {
    val viewModel = koinViewModel<HomeViewModel>()
    println("HomeScreen = ${viewModel}")
    val notes by viewModel.notes.collectAsState()
    val folders by viewModel.folders.collectAsState()

    var showFolderMenu by remember{mutableStateOf(false)}
    var showAddFolderDialog by remember {  mutableStateOf(false)}
    val lifecycleOwner = LocalLifecycleOwner.current

    val setTopBar = LocalTopBarData.current
    val setBottomBar = LocalBottomBarData.current

    LaunchedEffect(Unit) {
        setTopBar(
            TopBarData(
                title = "Home",
            ),
        )

        setBottomBar(
            BottomBarData(
                content = {
                    Box {
                        TextButton(onClick = {
                            showFolderMenu = true
                        }) {
                            Text("Folder")
                        }

                        DropdownMenu(
                            expanded = showFolderMenu,
                            onDismissRequest = { showFolderMenu = false }
                        ) {
                            folders.forEach { folder ->
                                DropdownMenuItem(
                                    text = { Text("${folder}") },
                                    onClick = {
                                        showFolderMenu = false
                                    }
                                )
                            }
                            DropdownMenuItem(
                                text = { Text("Add Folder") },
                                onClick = {
                                    println("Add Folder")
                                    showAddFolderDialog = true
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = {
                        onNavigateToAddNote()
                    }) {
                        Text("New Note")
                    }
                }
            )
        )
    }

    LifecycleResumeEffect(key1 = lifecycleOwner) {
        viewModel.loadNotes()
        onPauseOrDispose {
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NoteListScreen(notes = notes)
    }
    if (showAddFolderDialog) {
        AddFolderDialog(
            onDismissRequest = { showAddFolderDialog = false },
            onConfirmRequest = { folderName ->
                viewModel.addFolder(folderName)
            }
        )
    }
}

@Composable
fun NoteListScreen(
    notes: List<Note>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(notes) { note ->
            NoteItem(content = note)
        }
    }
}

@Composable
fun NoteItem(content: Note) {
    OutlinedCard(
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text("${content.title}", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
            Text(
                "${content.createdAt.toYyyyMmDd()}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline
            )
            Text("${content.body}")
        }
    }
}

@Composable
fun AddFolderDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmRequest: (String) -> Unit = {}
) {
    var folderName by mutableStateOf("")

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(text = "Create Folder", style = MaterialTheme.typography.titleLarge)
                TextField(
                    value = folderName,
                    onValueChange = { folderName = it },
                    placeholder = { Text("Folder Name") }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                    TextButton(onClick = {
                        onConfirmRequest(folderName)
                        onDismissRequest()
                    }) {
                        Text("Create")
                    }
                }
            }
        }
    }
}