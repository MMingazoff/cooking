package ru.itis.cooking.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.ui.components.AppImage
import ru.itis.cooking.core.ui.components.AppText
import ru.itis.cooking.core.ui.theme.DescColor
import ru.itis.cooking.core.ui.theme.Orange
import ru.itis.cooking.core.ui.utils.toCleanString

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    food: Food,
    onItemClicked: (Food) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .height(185.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onItemClicked(food) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AppImage(modifier = Modifier.weight(1f), url = food.image, indicatorSize = 30)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.1f)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppText(
                    text = food.title,
                    size = 20,
                    color = MaterialTheme.colorScheme.onSecondary,
                    maxLine = 2
                )
                AppText(
                    text = food.description.toCleanString(),
                    size = 14,
                    color = DescColor,
                    maxLine = 3
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    RecipeIcon(
                        icon = R.drawable.ic_baseline_favorite_24,
                        text = food.likeCount.toString(), // food.likeCount.toString(), -> only zero
                        iconColor = Color.Red
                    )
                    RecipeIcon(
                        icon = R.drawable.ic_baseline_access_time_24,
                        text = food.time.toString(),
                        iconColor = Orange
                    )
                    RecipeIcon(
                        icon = R.drawable.ic_baseline_energy_savings_leaf_24,
                        text = "Vegan",
                        iconColor = if (food.vegan) Color.Green else Color.Gray
                    )
                }
            }
        }
    }
}
