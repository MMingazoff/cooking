package ru.itis.cooking.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.cooking.core.data.util.Constants
import ru.itis.cooking.core.domain.model.FoodIngredient
import ru.itis.cooking.core.ui.components.AppImage
import ru.itis.cooking.core.ui.theme.ItimFont

@Composable
fun IngredientItem(
    ingredient: FoodIngredient,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(125.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AppImage(
                modifier = Modifier.weight(1.1f),
                url = "${Constants.BASE_IMAGE_URL}/${ingredient.ingImage}",
                contentScale = ContentScale.Fit,
                indicatorWidth = 2,
                imagePadding = 3
            )
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxSize()
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                IngText(
                    text = ingredient.aisle.toString(),
                    size = 16
                )
                IngText(
                    text = ingredient.consistency.lowercase(),
                    size = 14
                )
                IngText(
                    text = "${ingredient.amount} ${ingredient.unit}",
                    size = 14
                )
                IngText(
                    text = ingredient.original,
                    size = 14
                )
            }
        }
    }
}

@Composable
fun IngText(
    text: String,
    size: Int,
) {
    Text(
        text = text,
        fontSize = (size).sp,
        color = MaterialTheme.colorScheme.onSecondary,
        maxLines = 1,
        fontFamily = ItimFont,
        overflow = TextOverflow.Ellipsis
    )
}
