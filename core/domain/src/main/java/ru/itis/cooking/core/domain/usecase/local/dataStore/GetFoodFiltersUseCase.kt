package ru.itis.cooking.core.domain.usecase.local.dataStore

import kotlinx.coroutines.flow.Flow
import ru.itis.cooking.core.domain.model.FoodFilters
import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class GetFoodFiltersUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    operator fun invoke(): Flow<FoodFilters> = repository.getFoodFilters()
}
