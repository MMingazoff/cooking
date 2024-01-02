package ru.itis.cooking.core.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val DarkColorPalette = darkColorScheme(
    primary = DarkColor,
    onSecondary = PurpleGrey80,
    onTertiary = DarkColor,
    onTertiaryContainer = LightGray
)

private val LightColorPalette = lightColorScheme(
    primary = LightColor,
    onSecondary = DarkColor,
    onTertiary = Color.White,
    onTertiaryContainer = LightColor
    /*
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CookingTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
