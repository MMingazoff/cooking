package ru.itis.cooking.core.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.itis.cooking.core.data.remote.model.FoodResponseDTO

interface FoodService {
    @GET("recipes/complexSearch")
    suspend fun getAllRecipes(
        @Query("query") query: String?,
        @Query("number") number: Int,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("fillIngredients") fillIngredients: Boolean = true,
        @Query("type") type: String,
        @Query("diet") diet: String,
    ): FoodResponseDTO
}
