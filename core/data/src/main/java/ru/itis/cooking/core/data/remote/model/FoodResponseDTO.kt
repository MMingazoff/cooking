package ru.itis.cooking.core.data.remote.model

data class FoodResponseDTO(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)
