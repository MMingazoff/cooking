package ru.itis.cooking.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ru.itis.cooking.core.domain.model.FoodFilters
import ru.itis.cooking.core.ui.components.AppText
import ru.itis.cooking.core.ui.components.Chip

@Composable
fun SheetContent(
    onChipClicked: (FoodFilters) -> Unit,
    foodFilters: FoodFilters,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .height(350.dp)
            .semantics {
                contentDescription = "SheetContent"
            },
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AppText(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.food_filters_meal_type_title),
            size = 20,
            color = MaterialTheme.colorScheme.onSecondary
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(FoodFilters.mealTypes) { index, item ->
                Chip(
                    isSelected = foodFilters.mIndex == index,
                    onClicked = {
                        onChipClicked(
                            FoodFilters(
                                mIndex = index,
                                mType = item,
                                dIndex = foodFilters.dIndex,
                                dType = foodFilters.dType
                            )
                        )
                    },
                    text = item
                )
            }
        }
        AppText(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.food_filters_diet_type_title),
            size = 20,
            color = MaterialTheme.colorScheme.onSecondary
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(FoodFilters.dietTypes) { index, item ->
                Chip(
                    isSelected = foodFilters.dIndex == index,
                    onClicked = {
                        onChipClicked(
                            FoodFilters(
                                dIndex = index,
                                dType = item,
                                mIndex = foodFilters.mIndex,
                                mType = foodFilters.mType
                            )
                        )
                    },
                    text = item
                )
            }
        }
    }
}
