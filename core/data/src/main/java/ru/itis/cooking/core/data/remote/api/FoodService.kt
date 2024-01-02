package ru.itis.cooking.core.data.remote.api

import ru.itis.cooking.core.data.remote.model.FoodResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodService {
    @GET("recipes/complexSearch")
    suspend fun getAllRecipes(
        @QueryMap map: Map<String, String>
    ): Response<FoodResponseDTO>
}
