package ru.itis.cooking.core.data.repository

import ru.itis.cooking.core.data.mapper.toFood
import ru.itis.cooking.core.data.remote.api.FoodService
import ru.itis.cooking.core.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: FoodService
): RemoteRepository {
    override suspend fun getAllRecipes(
        query: String?,
        number: Int,
        type: String,
        diet: String,
    ) = runCatching {
        service.getAllRecipes(
            query = query,
            number = number,
            type = type,
            diet = diet
        ).results.map { it.toFood() }
    }
}
