package ru.itis.cooking.core.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.itis.cooking.core.domain.model.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFavoriteFood(food: Food)

    @Query("SELECT * FROM Food ORDER BY id DESC")
    fun getAllFavorites(): Flow<List<Food>>

    @Query("SELECT * FROM Food WHERE foodId = :id")
    fun getFoodById(id: Int): Flow<Food?>

    @Query("DELETE FROM Food WHERE foodId = :foodId")
    suspend fun deleteFavoriteFood(foodId: Int)

    @Query("DELETE FROM Food")
    suspend fun deleteAllFavoriteFoods()
}
