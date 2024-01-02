package ru.itis.cooking.core.data.repository

import ru.itis.cooking.core.data.mapper.toFood
import ru.itis.cooking.core.data.remote.api.FoodService
import ru.itis.cooking.core.domain.repository.RemoteRepository
import ru.itis.cooking.core.domain.util.DataResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: FoodService
): RemoteRepository {
    override suspend fun getAllRecipes(queryMap: Map<String, String>) = flow {
        emit(DataResult.Loading)
        try {
            val response = service.getAllRecipes(queryMap)
            if (response.isSuccessful) {
                response.body()?.results?.map { it.toFood() }?.let {
                    emit(DataResult.Success(it))
                }
            }
        } catch (e: Exception) {
            emit(DataResult.Error(e.message.toString()))
        }
    }
}
