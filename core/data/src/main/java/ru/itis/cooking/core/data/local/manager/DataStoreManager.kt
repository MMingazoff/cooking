package ru.itis.cooking.core.data.local.manager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.itis.cooking.core.domain.model.FoodFilters

class DataStoreManager(private val context: Context) {

    suspend fun saveMealType(foodFilters: FoodFilters) {
        context.dataStore.edit {
            it[mIndex] = foodFilters.mIndex
            it[mType] = foodFilters.mType
            it[dIndex] = foodFilters.dIndex
            it[dType] = foodFilters.dType
        }
    }

    fun getFoodFilters() = context.dataStore.data.map {
        FoodFilters(
            mIndex = it[mIndex] ?: 0,
            mType = it[mType] ?: "Main Course",
            dIndex = it[dIndex] ?: 0,
            dType = it[dType] ?: "Gluten Free"
        )
    }

    suspend fun saveTheme(theme: String) {
        context.dataStore.edit {
            it[themeKey] = theme
        }
    }

    fun getTheme(): Flow<String> = context.dataStore.data.map {
        it[themeKey] ?: ""
    }

    suspend fun saveUserVisiting(boolean: Boolean) {
        context.dataStore.edit {
            it[isUserVisited] = boolean
        }
    }

    fun getUserVisiting(): Flow<Boolean> = context.dataStore.data.map {
        it[isUserVisited] ?: false
    }

    companion object {

        private val Context.dataStore by preferencesDataStore("DishDataStore")

        private val mIndex = intPreferencesKey("mIndex")
        private val mType = stringPreferencesKey("mType")
        private val dIndex = intPreferencesKey("dIndex")
        private val dType = stringPreferencesKey("dType")

        private val themeKey = stringPreferencesKey("theme")

        private val isUserVisited = booleanPreferencesKey("isUserVisited")
    }
}
