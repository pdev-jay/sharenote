package com.pdevjay.sharenote.presentation.add

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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.pdevjay.sharenote.presentation.appbar.BottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalBottomBarData
import com.pdevjay.sharenote.presentation.appbar.LocalTopBarData
import com.pdevjay.sharenote.presentation.appbar.TopBarData
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddNoteScreen(
    folderId: Long,
    onPopBackStack: () -> Unit = {}
) {
    val noteViewModel = koinViewModel<AddViewModel>()

    val titleFocusRequester = remember { FocusRequester() }
    val contentFocusRequester = remember { FocusRequester() }


    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    val setTopBar = LocalTopBarData.current
    val setBottomBar = LocalBottomBarData.current

    LaunchedEffect(Unit) {
        setTopBar(
            TopBarData(
                title = "Add",
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
                        noteViewModel.addNote(folderId = folderId, title = title, body = content)
                        onPopBackStack()
                    },
                        enabled = title.isNotBlank() && content.isNotBlank()
                    ) {
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

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
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
            value = content,
            onValueChange = { content = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}