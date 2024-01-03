package ru.itis.cooking.onboarding.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ru.itis.cooking.core.navigation.Graph
import ru.itis.cooking.core.ui.theme.AppFont
import ru.itis.cooking.onboarding.R

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val viewModel: SplashViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(state) {
        val route = if (state.hasUserViewedOnboarding) {
            Graph.MainGraph.graph
        } else {
            Graph.SplashGraph.Onboarding.route
        }
        delay(2000L)
        navHostController.navigate(route = route) {
            popUpTo(Graph.SplashGraph.graph) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.splash_screen_title),
            fontSize = 60.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = AppFont
        )
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Splash Logo"
        )
    }
}
