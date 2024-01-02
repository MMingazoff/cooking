package ru.itis.cooking.core.data.remote.model

data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)
