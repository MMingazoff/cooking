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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ru.itis.cooking.core.data.util.Constants
import ru.itis.cooking.core.domain.model.FoodType
import ru.itis.cooking.core.ui.components.AppText
import ru.itis.cooking.core.ui.components.Chip

@Composable
fun SheetContent(
    onApplyClicked: () -> Unit,
    onChipClicked: (FoodType) -> Unit,
    foodState: FoodType,
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
            text = "Meal Type",
            size = 20,
            color = MaterialTheme.colorScheme.onSecondary
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(Constants.mealTypes) { index, item ->
                Chip(
                    isSelected = foodState.mIndex == index,
                    onClicked = {
                        onChipClicked(
                            FoodType(
                                mIndex = index,
                                mType = item,
                                dIndex = foodState.dIndex,
                                dType = foodState.dType
                            )
                        )
                    },
                    text = item
                )
            }
        }
        AppText(
            modifier = Modifier.padding(start = 15.dp),
            text = "Diet Type",
            size = 20,
            color = MaterialTheme.colorScheme.onSecondary
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(Constants.dietTypes) { index, item ->
                Chip(
                    isSelected = foodState.dIndex == index,
                    onClicked = {
                        onChipClicked(
                            FoodType(
                                dIndex = index,
                                dType = item,
                                mIndex = foodState.mIndex,
                                mType = foodState.mType
                            )
                        )
                    },
                    text = item
                )
            }
        }
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 20.dp)
                .semantics {
                    contentDescription = "ApplyBtn"
                },
            onClick = {
                onApplyClicked()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        ) {
            AppText(
                text = "Apply",
                size = 18,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}
