package ru.itis.cooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.cooking.core.domain.model.Theme
import ru.itis.cooking.core.domain.usecase.base.AllUseCases
import ru.itis.cooking.core.ui.theme.CookingTheme
import ru.itis.cooking.presentation.navigation.RootNavigation
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var allUseCases: AllUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme = allUseCases.getThemeUseCase().collectAsStateWithLifecycle(
                initialValue = Theme.AUTOMATIC,
                lifecycle = lifecycle,
            ).value
            CookingTheme(theme = theme) {
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
