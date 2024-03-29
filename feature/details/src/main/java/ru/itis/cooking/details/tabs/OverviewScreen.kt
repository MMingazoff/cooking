package ru.itis.cooking.details.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.ui.components.AppText
import ru.itis.cooking.core.ui.components.DetailChip
import ru.itis.cooking.core.ui.theme.DarkColor
import ru.itis.cooking.core.ui.theme.ItimFont
import ru.itis.cooking.core.ui.utils.toCleanString
import ru.itis.cooking.details.R

@Composable
fun OverviewScreen(
    nullableFood: Food?,
) {
    val food by remember {
        mutableStateOf(nullableFood!!)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onTertiary)
            .verticalScroll(rememberScrollState())
    ) {
        val painter = rememberCoilPainter(request = food.image)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    DarkColor
                                )
                            )
                        )
                        .padding(top = 8.dp, bottom = 12.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    RecipeIcon(
                        icon = R.drawable.ic_baseline_favorite_24,
                        text = food.likeCount.toString(),
                        iconColor = Color.White
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    RecipeIcon(
                        icon = R.drawable.ic_baseline_access_time_24,
                        text = food.time.toString(),
                        iconColor = Color.White
                    )
                }
            }
        }
        DetailSection(food)
    }
}

@Composable
fun DetailSection(food: Food) {
    val statistics = mapOf(
        "Vegetarian" to food.vegetarian,
        "Vegan" to food.vegan,
        "Gluten Free" to food.glutenFree,
        "Dairy Free" to food.dairyFree,
        "Healthy" to food.veryHealthy,
        "Cheap" to food.cheap
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        AppText(
            text = food.title,
            size = 20,
            color = MaterialTheme.colorScheme.onSecondary,
            maxLine = 3
        )
        Spacer(modifier = Modifier.height(5.dp))
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            rows = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(statistics.toList()) {
                DetailChip(text = it.first, isTrue = it.second)
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = food.description.toCleanString(),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = ItimFont
        )
    }
}
