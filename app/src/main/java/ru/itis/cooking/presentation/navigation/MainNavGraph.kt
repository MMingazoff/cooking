package ru.itis.cooking.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.navigation.Graph
import ru.itis.cooking.details.main.DetailScreen
import ru.itis.cooking.favourites.FavoriteScreen
import ru.itis.cooking.onboarding.OnboardingScreen
import ru.itis.cooking.onboarding.splash.SplashScreen
import ru.itis.cooking.recipes.RecipesScreen
import ru.itis.cooking.settings.SettingsScreen

fun NavGraphBuilder.splashNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.SplashGraph.graph,
        startDestination = Graph.SplashGraph.Splash.route
    ) {
        composable(route = Graph.SplashGraph.Splash.route) {
            SplashScreen(navHostController)
        }
        composable(route = Graph.SplashGraph.Onboarding.route) {
            OnboardingScreen(navHostController)
        }
    }
}

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.MainGraph.graph,
        startDestination = BottomBarScreen.Recipes.route
    ) {
        composable(route = BottomBarScreen.Recipes.route) {
            RecipesScreen(navHostController)
        }
        composable(route = BottomBarScreen.Favorites.route) {
            FavoriteScreen(navHostController)
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
        detailsNavGraph(navHostController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.DetailsGraph.graph,
        startDestination = Graph.DetailsGraph.Details.route
    ) {
        composable(route = Graph.DetailsGraph.Details.route) {
            val food = navHostController.previousBackStackEntry?.savedStateHandle?.get<Food>("food")
            DetailScreen(navHostController = navHostController, food = food)
        }
    }
}
