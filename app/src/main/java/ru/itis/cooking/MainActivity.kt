package ru.itis.cooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ru.itis.cooking.presentation.navigation.RootNavigation
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.cooking.core.ui.theme.CookingTheme
import ru.itis.cooking.settings.SettingsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            val theme = when (viewModel.themeState.collectAsState().value) {
                1 -> false
                2 -> true
                else -> isSystemInDarkTheme()
            }
            CookingTheme(darkTheme = theme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigation(navHostController = rememberNavController())
                }
            }
        }
    }
}
