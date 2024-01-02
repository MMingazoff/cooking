package ru.itis.cooking.core.data.mapper

import ru.itis.cooking.core.data.remote.model.AnalyzedInstructionsDTOItem
import ru.itis.cooking.core.data.remote.model.ExtendedIngredient
import ru.itis.cooking.core.data.remote.model.Ingredient
import ru.itis.cooking.core.data.remote.model.Result
import ru.itis.cooking.core.data.remote.model.Step
import ru.itis.cooking.core.domain.model.AnalyzedInstructions
import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.model.FoodIngredient
import ru.itis.cooking.core.domain.model.InsIngredient
import ru.itis.cooking.core.domain.model.InsStep
import java.util.Random

fun Result.toFood(): Food {
    return Food(
        id = 0,
        foodId = foodId,
        title = title,
        image = image,
        description = summary,
        likeCount = Random().nextInt(1000),// we use random like count because food like incoming only zero from backend
        time = readyInMinutes,
        vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy,
        cheap = cheap,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        ingredients = extendedIngredients.map { it.toIngredient() },
        analyzedIns = analyzedInstructions.map { it.toAnalyzed() }
    )
}

fun ExtendedIngredient.toIngredient(): FoodIngredient {
    return FoodIngredient(
        id = id,
        aisle = aisle,
        consistency = consistency,
        ingImage = image,
        name = name,
        nameClean = nameClean,
        original = original,
        originalName = originalName,
        unit = unit,
        amount = amount,
    )
}

fun AnalyzedInstructionsDTOItem.toAnalyzed(): AnalyzedInstructions {
    return AnalyzedInstructions(
        steps = steps.map { it.toStep() }
    )
}

fun Step.toStep(): InsStep {
    return InsStep(
        ingredients = ingredients.map { it.toInsIngredient() },
        number = number,
        step = step
    )
}
fun Ingredient.toInsIngredient(): InsIngredient {
    return InsIngredient(
        id = id,
        name = name,
        image = image
    )
}
