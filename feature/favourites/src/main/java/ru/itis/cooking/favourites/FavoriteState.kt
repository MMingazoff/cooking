package ru.itis.cooking.favourites

import ru.itis.cooking.core.domain.model.Food

data class FavoriteState(
    val isLoading: Boolean = false,
    val foodList: List<Food> = emptyList()
)
