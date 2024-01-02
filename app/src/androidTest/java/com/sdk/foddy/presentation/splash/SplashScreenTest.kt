package com.sdk.foddy.presentation.splash

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import ru.itis.cooking.di.DatabaseModule
import ru.itis.cooking.di.NetworkModule
import ru.itis.cooking.presentation.navigation.RootNavigation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.itis.cooking.MainActivity
import ru.itis.cooking.core.navigation.Graph
import ru.itis.cooking.core.ui.theme.CookingTheme

@HiltAndroidTest
@UninstallModules(DatabaseModule::class, NetworkModule::class)
class SplashScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navHostController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            navHostController = TestNavHostController(LocalContext.current)
            CookingTheme(darkTheme = true) {
                navHostController.navigatorProvider.addNavigator(ComposeNavigator())
                RootNavigation(navHostController = navHostController)
            }
        }
    }

    @Test
    fun checkTheme_and_TitleWithImage() {
        composeRule.onNodeWithText("Palov").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Splash Logo").assertIsDisplayed()
    }

    @Test
    fun ifUserAlreadyVisited_navigateToMainScreen() {
        val route = navHostController.currentBackStackEntry?.destination?.route
        Assert.assertEquals(route, Graph.MainGraph.graph)
    }
}
