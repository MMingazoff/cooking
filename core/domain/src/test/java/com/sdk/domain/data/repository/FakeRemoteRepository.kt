package com.sdk.domain.data.repository

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.RemoteRepository
import ru.itis.cooking.core.domain.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRemoteRepository : RemoteRepository {
    val foodList = mutableListOf<Food>()

    init {
        foodList.add(
            Food(
                id = 0,
                foodId = 1,
                title = "Blah",
                image = "https://image.jpg",
                description = "Hehe",
                likeCount = 100,
                time = 1000,
                vegan = false,
                vegetarian = false,
                veryHealthy = true,
                dairyFree = false,
                cheap = false,
                glutenFree = true,
                ingredients = emptyList(),
                analyzedIns = emptyList()
            )
        )
    }

    override suspend fun getAllRecipes(queryMap: Map<String, String>): Flow<DataResult<List<Food>>> {
        return flow {
            if (queryMap.isEmpty()) {
                emit(DataResult.Error("Query map shouldn't be empty!"))
            } else {
                emit(DataResult.Success(foodList))
            }
        }
    }
}
