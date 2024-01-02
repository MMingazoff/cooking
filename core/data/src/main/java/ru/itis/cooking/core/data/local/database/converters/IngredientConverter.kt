package ru.itis.cooking.core.data.local.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.itis.cooking.core.domain.model.FoodIngredient

class IngredientConverter {

    @TypeConverter
    fun fromList(mutableList: MutableList<FoodIngredient>): String {
        val type = object : TypeToken<MutableList<FoodIngredient>>() {}.type
        return Gson().toJson(mutableList, type)
    }
    @TypeConverter
    fun  toList(json: String): MutableList<FoodIngredient> {
        val type = object : TypeToken<MutableList<FoodIngredient>>() {}.type
        return Gson().fromJson(json, type)
    }
}
