package ru.itis.cooking.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.itis.cooking.presentation.navigation.MainNavGraph
import ru.itis.cooking.presentation.screens.BottomBar

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navHostController)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            MainNavGraph(navHostController = navHostController)
        }
    }
}
