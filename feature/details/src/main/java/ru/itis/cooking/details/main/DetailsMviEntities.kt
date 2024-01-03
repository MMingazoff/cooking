package ru.itis.cooking.details.main

import ru.itis.cooking.core.domain.model.Food

data class DetailsState(
    val isFoodSaved: Boolean = false,
)

sealed class DetailsAction

sealed class DetailsEvent {
    data class OnCheckFood(val id: Int): DetailsEvent()
    data class OnUpdateFood(val food: Food): DetailsEvent()
}
