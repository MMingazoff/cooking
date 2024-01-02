package ru.itis.cooking.recipes

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ru.itis.cooking.core.ui.components.SearchAppBar
import ru.itis.cooking.core.ui.theme.AppFont

@Composable
fun MainTopBar(
    searchWidgetState: SearchWidgetState,
    onActionClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {

    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            TopAppBar(
                title = {
                    Text(
                        text = "Recipes",
                        fontFamily = AppFont,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.primary,
                actions = {
                    IconButton(onClick = {
                        onActionClicked()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                onCloseClicked = {
                    onCloseClicked()
                },
                onSearchClicked = {
                   onSearchClicked(it)
                }
            )
        }
    }
}
