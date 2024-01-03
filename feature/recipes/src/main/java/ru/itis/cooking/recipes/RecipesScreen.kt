package ru.itis.cooking.recipes

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.itis.cooking.core.navigation.Graph
import ru.itis.cooking.core.ui.components.ErrorMessage
import ru.itis.cooking.core.ui.components.LoadingIcon
import ru.itis.cooking.core.ui.theme.Purple80
import ru.itis.cooking.recipes.uiElements.MainTopBar
import ru.itis.cooking.recipes.uiElements.RecipeItem

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreen(navHostController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current as Activity
    val viewModel: RecipesViewModel = hiltViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchState by remember {
        mutableStateOf(SearchWidgetState.CLOSED)
    }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    BackHandler {
        if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
            coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        } else {
            context.finish()
        }
    }

    BottomSheetScaffold(
        modifier = Modifier.semantics {
            testTag = "RecipeBackground"
        },
        topBar = {
            MainTopBar(
                searchWidgetState = searchState,
                onActionClicked = {
                    searchState = SearchWidgetState.OPENED
                    keyboardController?.show()
                },
                onCloseClicked = {
                    searchState = SearchWidgetState.CLOSED
                    keyboardController?.hide()
                },
                onSearchClicked = {
                    viewModel.accept(RecipesEvent.OnSearchFood(it))
                }
            )
        },
        sheetContent = {
            SheetContent(
                foodFilters = state.foodFilters,
                onChipClicked = { foodType ->
                    viewModel.accept(RecipesEvent.OnSaveFoodType(foodType))
                },
            )
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp
    ) {
        Surface(
            modifier = Modifier.blur(
                if (bottomSheetScaffoldState.bottomSheetState.isExpanded) 5.dp else 0.dp
            ),
            onClick = {
                if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            },
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            LazyColumn(
                contentPadding = PaddingValues(all = 5.dp),
                modifier = Modifier.semantics {
                    testTag = "RecipeScreenLazyColumn"
                }
            ) {
                if (!state.isLoading) {
                    items(
                        items = state.foodList,
                        key = { it.foodId }
                    ) {
                        RecipeItem(
                            food = it,
                            onItemClicked = { food ->
                                if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                                    coroutineScope.launch {
                                        bottomSheetScaffoldState.bottomSheetState.collapse()
                                    }
                                } else {
                                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                        "food",
                                        food
                                    )
                                    navHostController.navigate(Graph.DetailsGraph.graph)
                                }
                            }
                        )
                    }
                }
            }
            BottomSection(
                onFabClicked = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                },
                isLoading = state.isLoading,
                error = state.errorMessage,
                isListEmpty = state.foodList.isEmpty()
            )
        }
    }
}

@Composable
fun BottomSection(
    onFabClicked: () -> Unit,
    error: String,
    isLoading: Boolean,
    isListEmpty: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = onFabClicked,
            containerColor = Purple80,
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_restaurant_menu_24),
                contentDescription = "MealFab"
            )
        }
    }
    if (error.isNotBlank() && isListEmpty) {
        ErrorMessage(error)
    }
    if (isLoading) {
        LoadingIcon()
    }
}
