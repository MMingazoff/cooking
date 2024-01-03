package ru.itis.cooking.onboarding

import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ru.itis.cooking.core.navigation.Graph
import ru.itis.cooking.core.ui.components.AppText
import ru.itis.cooking.core.ui.components.LottieAnim
import ru.itis.cooking.core.ui.theme.ItimFont
import ru.itis.cooking.onboarding.splash.SplashScreenEvent
import ru.itis.cooking.onboarding.splash.SplashViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navHostController: NavHostController
) {
    val viewModel: SplashViewModel = hiltViewModel()
    val pagerState = rememberPagerState()
    val anim = listOf(
        R.raw.pager1,
        R.raw.pager2,
        R.raw.pager3
    )
    val map = listOf(
        stringResource(id = R.string.onboarding_page1_title) to stringResource(id = R.string.onboarding_page1_content),
        stringResource(id = R.string.onboarding_page2_title) to stringResource(id = R.string.onboarding_page2_content),
        stringResource(id = R.string.onboarding_page3_title) to stringResource(id = R.string.onboarding_page3_content),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            contentPadding = PaddingValues(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(2.5f)
        ) { index ->
            Page(title = map[index].first, content = map[index].second, anim = anim[index])
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Center
        ) {
            this@Column.AnimatedVisibility(
                visible = pagerState.currentPage == 2,
                exit = fadeOut(),
                enter = fadeIn()
            ) {
                ElevatedButton(
                    onClick = {
                        viewModel.accept(SplashScreenEvent.SetUserVisited)
                        navHostController.navigate(Graph.MainGraph.graph) {
                            popUpTo(Graph.SplashGraph.Onboarding.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier.size(height = 55.dp, width = 280.dp),
                    colors = ButtonDefaults.elevatedButtonColors(containerColor = MaterialTheme.colorScheme.onSecondary)
                ) {
                    AppText(
                        text = "Get Started",
                        size = 20,
                        color = Color.White,
                        fontFamily = ItimFont
                    )
                }
            }
            if (pagerState.currentPage != 2) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Composable
fun Page(
    title: String,
    content: String,
    @RawRes anim: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnim(anim = anim, modifier = Modifier.height(300.dp))
        Spacer(modifier = Modifier.height(20.dp))
        AppText(
            text = title,
            size = 32,
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = ItimFont
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = content,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = ItimFont,
            textAlign = TextAlign.Center
        )
    }
}
