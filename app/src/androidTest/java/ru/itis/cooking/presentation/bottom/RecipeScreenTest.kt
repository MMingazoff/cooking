package ru.itis.cooking.presentation.bottom

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.itis.cooking.MainActivity
import ru.itis.cooking.core.domain.model.FoodFilters
import ru.itis.cooking.core.ui.theme.CookingTheme
import ru.itis.cooking.di.DatabaseModule
import ru.itis.cooking.di.NetworkModule
import ru.itis.cooking.recipes.RecipesScreen

@HiltAndroidTest
@UninstallModules(DatabaseModule::class, NetworkModule::class)
class RecipeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navHostController = rememberNavController()
            CookingTheme(darkTheme = true) {
                RecipesScreen(navHostController = navHostController)
            }
        }
    }

    @Test
    fun testRecipeScreen_lazyColumnIsDisplayed() {
        composeRule.onNodeWithTag(testTag = "RecipeScreenLazyColumn", useUnmergedTree = true).assertIsDisplayed()
        composeRule.onNodeWithTag("RecipeBackground").assertIsDisplayed()
    }

    @Test
    fun fabClicked_openBottomSheetDialog() {
        composeRule.onNodeWithContentDescription("MealFab").performClick()
        composeRule.onNodeWithContentDescription("SheetContent").assertIsDisplayed()
    }

    @Test
    fun selectFilterClicked_CheckLoading() {
        composeRule.onNodeWithText(FoodFilters.dietTypes.first()).performClick()
        composeRule.onNodeWithContentDescription("SheetContent").assertIsNotDisplayed()
        composeRule.onNodeWithTag("Loading").assertIsDisplayed()
    }
}
