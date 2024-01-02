package ru.itis.cooking.recipes

import ru.itis.cooking.core.domain.model.Food

data class RecipesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: List<Food> = emptyList()
)
