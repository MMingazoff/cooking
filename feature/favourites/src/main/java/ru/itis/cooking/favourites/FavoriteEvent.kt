package ru.itis.cooking.favourites

sealed class FavoriteEvent {
    object OnDeleteAllFavoriteFoods: FavoriteEvent()
}
