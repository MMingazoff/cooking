package ru.itis.cooking.details.main

import ru.itis.cooking.core.domain.model.Food

sealed class DetailEvent {
    data class OnCheckFood(val id: Int): DetailEvent()
    data class OnUpdateFood(val food: Food): DetailEvent()
}
