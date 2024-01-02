package ru.itis.cooking.recipes

import ru.itis.cooking.core.domain.model.FoodType

sealed class RecipeEvent {
    data class GetAllRecipes(val foodType: FoodType): RecipeEvent()
    data class OnSearchFood(val query: String,): RecipeEvent()
    data class OnSaveFoodType(val foodType: FoodType): RecipeEvent()
    object OnApplyClicked: RecipeEvent()
}
