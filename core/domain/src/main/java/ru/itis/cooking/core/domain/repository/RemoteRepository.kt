package ru.itis.cooking.core.domain.repository

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.util.DataResult
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getAllRecipes(queryMap: Map<String, String>): Flow<DataResult<List<Food>>>
}
