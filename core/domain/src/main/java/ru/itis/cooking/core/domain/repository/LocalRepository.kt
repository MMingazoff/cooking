package ru.itis.cooking.core.domain.repository

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.model.FoodFilters
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveFoodType(foodFilters: FoodFilters)
    fun getFoodFilters(): Flow<FoodFilters>
    suspend fun saveTheme(index: Int)
    fun getTheme(): Flow<Int>
    suspend fun saveUserVisiting(boolean: Boolean)
    fun getUserVisiting(): Flow<Boolean>
    suspend fun saveFavoriteFood(food: Food)
    fun getFoodById(id: Int): Flow<Food?>
    fun getFoodList(): Flow<List<Food>>
    suspend fun deleteFood(foodId: Int)
    suspend fun deleteAllFavoriteFoods()
}