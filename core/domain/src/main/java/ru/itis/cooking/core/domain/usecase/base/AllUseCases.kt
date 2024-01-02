package ru.itis.cooking.core.domain.usecase.base

import ru.itis.cooking.core.domain.usecase.remote.GetAllRecipesUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetFoodTypeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetThemeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetUserVisitingUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveFoodTypeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveThemeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveUserVisitingUseCase
import ru.itis.cooking.core.domain.usecase.local.room.DeleteAllFavoriteFoodsUseCase
import ru.itis.cooking.core.domain.usecase.local.room.DeleteFavoriteFoodUseCase
import ru.itis.cooking.core.domain.usecase.local.room.GetFavoriteFoodByIdUseCase
import ru.itis.cooking.core.domain.usecase.local.room.GetFavoriteFoodsUseCase
import ru.itis.cooking.core.domain.usecase.local.room.SaveFavoriteFoodUseCase

data class AllUseCases(
    val getAllRecipesUseCase: GetAllRecipesUseCase,
    val getFoodTypeUseCase: GetFoodTypeUseCase,
    val saveFoodTypeUseCase: SaveFoodTypeUseCase,
    val deleteFavoriteFoodUseCase: DeleteFavoriteFoodUseCase,
    val getFavoriteFoodByIdUseCase: GetFavoriteFoodByIdUseCase,
    val getFavoriteFoodsUseCase: GetFavoriteFoodsUseCase,
    val saveFavoriteFoodUseCase: SaveFavoriteFoodUseCase,
    val deleteAllFavoriteFoodsUseCase: DeleteAllFavoriteFoodsUseCase,
    val saveThemeUseCase: SaveThemeUseCase,
    val getThemeUseCase: GetThemeUseCase,
    val saveUserVisitingUseCase: SaveUserVisitingUseCase,
    val getUserVisitingUseCase: GetUserVisitingUseCase
)
