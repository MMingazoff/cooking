package com.sdk.domain.data.repository

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.model.FoodFilters
import ru.itis.cooking.core.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.itis.cooking.core.domain.model.Theme

class FakeLocalRepository : LocalRepository {

    private val fakeFoodList = mutableListOf<Food>()
    private var foodFilters = FoodFilters()
    private var isUserVisited = false
    private var savedTheme = Theme.AUTOMATIC

    override suspend fun saveFoodType(foodFilters: FoodFilters) {
        this.foodFilters = foodFilters
    }

    override fun getFoodFilters(): Flow<FoodFilters> {
        return flow { emit(foodFilters) }
    }

    override suspend fun saveTheme(theme: Theme) {
        savedTheme = theme
    }

    override fun getTheme(): Flow<Theme> {
        return flow { emit(savedTheme) }
    }

    override suspend fun saveUserVisiting(boolean: Boolean) {
        isUserVisited = boolean
    }

    override fun getUserVisiting(): Flow<Boolean> {
        return flow { emit(isUserVisited) }
    }

    override suspend fun saveFavoriteFood(food: Food) {
        fakeFoodList.add(food)
    }

    override fun getFoodById(id: Int): Flow<Food?> {
        return flow {
            emit(fakeFoodList.find { it.foodId == id })
        }
    }

    override fun getFoodList(): Flow<List<Food>> {
        return flow {
            emit(fakeFoodList)
        }
    }

    override suspend fun deleteFood(foodId: Int) {
        fakeFoodList.removeIf { it.foodId == foodId }
    }

    override suspend fun deleteAllFavoriteFoods() {
        fakeFoodList.clear()
    }
}
