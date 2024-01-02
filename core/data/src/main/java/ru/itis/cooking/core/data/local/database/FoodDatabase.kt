package ru.itis.cooking.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.itis.cooking.core.data.local.database.converters.AnalyzedInstructionsConverter
import ru.itis.cooking.core.data.local.database.converters.IngredientConverter
import ru.itis.cooking.core.domain.model.Food

@Database(entities = [Food::class], version = 1, exportSchema = false)
@TypeConverters(IngredientConverter::class, AnalyzedInstructionsConverter::class)
abstract class FoodDatabase: RoomDatabase() {
    abstract val dao: FoodDao
}
