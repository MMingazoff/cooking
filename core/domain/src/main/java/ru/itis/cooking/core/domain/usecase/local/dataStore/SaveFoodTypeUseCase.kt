package ru.itis.cooking.core.domain.usecase.local.dataStore

import ru.itis.cooking.core.domain.model.FoodFilters
import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class SaveFoodTypeUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(foodFilters: FoodFilters) = repository.saveFoodType(foodFilters)
}
