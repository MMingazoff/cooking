package ru.itis.cooking.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.itis.cooking.core.data.local.database.FoodDao
import ru.itis.cooking.core.data.local.manager.DataStoreManager
import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.model.FoodFilters
import ru.itis.cooking.core.domain.model.Theme
import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val dao: FoodDao
) : LocalRepository {

    override suspend fun saveFoodType(foodFilters: FoodFilters) {
        dataStoreManager.saveMealType(foodFilters)
    }

    override fun getFoodFilters(): Flow<FoodFilters> {
        return dataStoreManager.getFoodFilters()
    }

    override suspend fun saveTheme(theme: Theme) {
        dataStoreManager.saveTheme(theme.toString())
    }

    override fun getTheme(): Flow<Theme> {
        return dataStoreManager.getTheme().map {
            try {
                Theme.valueOf(it)
            } catch (_: IllegalArgumentException) {
                Theme.AUTOMATIC
            }
        }
    }

    override suspend fun saveUserVisiting(boolean: Boolean) {
        dataStoreManager.saveUserVisiting(boolean)
    }

    override fun getUserVisiting(): Flow<Boolean> {
        return dataStoreManager.getUserVisiting()
    }

    override suspend fun saveFavoriteFood(food: Food) {
        dao.saveFavoriteFood(food)
    }

    override fun getFoodById(id: Int): Flow<Food?> {
        return dao.getFoodById(id)
    }

    override fun getFoodList(): Flow<List<Food>> {
        return dao.getAllFavorites()
    }

    override suspend fun deleteFood(foodId: Int) {
        dao.deleteFavoriteFood(foodId)
    }

    override suspend fun deleteAllFavoriteFoods() {
        dao.deleteAllFavoriteFoods()
    }
}
