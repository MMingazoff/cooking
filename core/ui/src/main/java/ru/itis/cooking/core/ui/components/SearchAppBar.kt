package ru.itis.cooking.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
    var text by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = text,
            onValueChange = {
                text = it
            },

            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.5f),
                    text = "Search here...",
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotBlank()) {
                            onSearchClicked(text)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            )
        )
    }
}
