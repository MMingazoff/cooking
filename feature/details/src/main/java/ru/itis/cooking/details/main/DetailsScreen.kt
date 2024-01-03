package ru.itis.cooking.details.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.ui.theme.AppFont
import ru.itis.cooking.core.ui.theme.ItimFont
import ru.itis.cooking.details.R
import ru.itis.cooking.details.tabs.IngredientsScreen
import ru.itis.cooking.details.tabs.InstructionsScreen
import ru.itis.cooking.details.tabs.OverviewScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    food: Food?,
) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val tabs = listOf(
        stringResource(id = R.string.overview_tab),
        stringResource(id = R.string.ingredients_tab),
        stringResource(id = R.string.instructions_tab),
    )
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = true) {
        food?.let {
            viewModel.accept(DetailsEvent.OnCheckFood(it.foodId))
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Details",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontFamily = AppFont
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        food?.let {
                            viewModel.accept(DetailsEvent.OnUpdateFood(it))
                        }
                    }) {
                        Icon(
                            imageVector = if (state.isFoodSaved) {
                                Icons.Default.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                            contentDescription = "Favorite",
                            tint = if (state.isFoodSaved) Color.Red else MaterialTheme.colorScheme.onSecondary
                        )
                    }
                },
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = MaterialTheme.colorScheme.primary,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier.pagerTabIndicatorOffset(
                            pagerState,
                            it
                        ),
                        height = 3.dp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            ) {
                tabs.forEachIndexed { index, s ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = s,
                                color = MaterialTheme.colorScheme.onSecondary,
                                fontFamily = ItimFont,
                                fontSize = 17.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    )
                }
            }
            HorizontalPager(count = 3, state = pagerState) {
                when (it) {
                    0 -> OverviewScreen(nullableFood = food)
                    1 -> IngredientsScreen(ingredients = food?.ingredients)
                    2 -> InstructionsScreen(steps = food?.analyzedIns?.get(0)?.steps)
                }
            }
        }
    }
}
