package com.pdevjay.sharenote.presentation.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.pdevjay.sharenote.domain.model.Note
import com.pdevjay.sharenote.presentation.appbar.BottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalBottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalTopBarData
import com.pdevjay.sharenote.presentation.appbar.TopBarData
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteEditScreen(
    modifier: Modifier = Modifier,
    noteId: Long = -1L,
    onPopBackStack: () -> Unit = {},
) {
    val noteEditViewModel = koinViewModel<NoteEditViewModel>(){parametersOf(noteId)}

    val note by noteEditViewModel.selectedNote.collectAsState()

    val titleFocusRequester = remember { FocusRequester() }
    val contentFocusRequester = remember { FocusRequester() }

    var title by remember { mutableStateOf(note?.title ?: "") }
    var body by remember { mutableStateOf(note?.body ?: "") }

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
                                val newNote = note?.copy(
                                    title = title,
                                    body = body
                                )
                                if (note != null && newNote != null && newNote != note) {
                                    noteEditViewModel.updateNote(newNote)
                                    onPopBackStack()
                                }
                        }
                    ){
                        Text("Save")
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
    Column(modifier = modifier.fillMaxSize()) {
        TextField(
            modifier = Modifier.fillMaxWidth().focusRequester(titleFocusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    contentFocusRequester.requestFocus()
                }
            ),
            textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            placeholder = {
                Text(
                    "Title",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
            },
            value = title,
            onValueChange = { title = it },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        TextField(
            modifier = Modifier.fillMaxSize().focusRequester(contentFocusRequester),
            placeholder = { Text("Content") },
            value = body,
            onValueChange = { body = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}