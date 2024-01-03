package ru.itis.cooking.favourites

import ru.itis.cooking.core.domain.model.Food

data class FavoritesState(
    val isLoading: Boolean = false,
    val foodList: List<Food> = emptyList()
)

sealed class FavouritesAction

sealed class FavoritesEvent {
    object DeleteAllFavoriteFoods: FavoritesEvent()
    object GetAllFavoriteFoods: FavoritesEvent()
}
