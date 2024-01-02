package ru.itis.cooking.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.itis.cooking.presentation.MainScreen
import ru.itis.cooking.core.navigation.Graph

@Composable
fun RootNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.RootGraph.graph,
        startDestination = Graph.SplashGraph.graph
    ) {
        splashNavGraph(navHostController = navHostController)
        composable(route = Graph.MainGraph.graph) {
            MainScreen()
        }
    }
}
