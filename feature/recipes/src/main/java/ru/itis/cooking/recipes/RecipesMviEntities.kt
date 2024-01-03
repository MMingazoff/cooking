package ru.itis.cooking.recipes

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.model.FoodFilters

data class RecipesState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val foodList: List<Food> = emptyList(),
    val foodFilters: FoodFilters = FoodFilters(),
)

sealed interface RecipesAction

sealed interface RecipesEvent {
    object GetAllRecipes: RecipesEvent
    data class OnSearchFood(val query: String): RecipesEvent
    data class OnSaveFoodType(val foodFilters: FoodFilters): RecipesEvent
}
