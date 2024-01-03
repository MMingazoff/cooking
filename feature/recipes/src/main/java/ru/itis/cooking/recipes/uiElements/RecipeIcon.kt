package ru.itis.cooking.recipes.uiElements

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.itis.cooking.core.ui.theme.AppFont

@Composable
fun RecipeIcon(
    icon: Int,
    text: String,
    iconColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Icon",
            tint = iconColor
        )
        Text(text = text,color = iconColor, fontFamily = AppFont)
    }
}
