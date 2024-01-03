package ru.itis.cooking.core.domain.repository

import ru.itis.cooking.core.domain.model.Food

interface RemoteRepository {
    suspend fun getAllRecipes(
        query: String?,
        number: Int,
        type: String,
        diet: String,
    ): Result<List<Food>>
}
