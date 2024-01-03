package com.sdk.domain.data.repository

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.RemoteRepository

class FakeRemoteRepository : RemoteRepository {

    val foodList = listOf(
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
    var isError = false

    override suspend fun getAllRecipes(
        query: String?,
        number: Int,
        type: String,
        diet: String,
    ): Result<List<Food>> {
        return runCatching {
            if (isError) {
                error("Query map shouldn't be empty!")
            } else {
                foodList
            }
        }
    }
}
