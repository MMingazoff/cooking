package ru.itis.cooking.details.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.cooking.core.data.util.Constants
import ru.itis.cooking.core.domain.model.InsIngredient
import ru.itis.cooking.core.domain.model.InsStep
import ru.itis.cooking.core.ui.components.AppImage
import ru.itis.cooking.core.ui.components.AppText
import ru.itis.cooking.core.ui.theme.ItimFont
import ru.itis.cooking.details.R

@Composable
fun InstructionItem(
    insStep: InsStep,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        color = MaterialTheme.colorScheme.onTertiary
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            AppText(
                text = stringResource(id = R.string.step, insStep.number),
                size = 22,
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = ItimFont,
                maxLine = Int.MAX_VALUE
            )
            Spacer(modifier = Modifier.height(6.dp))
            AppText(
                text = insStep.step,
                size = 18,
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = ItimFont,
                maxLine = Int.MAX_VALUE
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items(insStep.ingredients) {
                    InstructionIngredientItem(insIngredient = it)
                }
            }
        }
    }
}

@Composable
fun InstructionIngredientItem(
    insIngredient: InsIngredient,
) {
    Column(
        modifier = Modifier.padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppText(
            text = insIngredient.name,
            size = 15,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.height(3.dp))
        AppImage(
            url = "${Constants.BASE_IMAGE_URL}/${insIngredient.image}",
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Fit,
            indicatorWidth = 2
        )
    }
}
