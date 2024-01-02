package ru.itis.cooking.core.data.remote.model

data class ExtendedIngredient(
    val aisle: String,
    val amount: Double,
    val consistency: String,
    val id: Int,
    val image: String,
    val meta: List<String>,
    val name: String?,
    val nameClean: String?,
    val original: String,
    val originalName: String,
    val unit: String
)
