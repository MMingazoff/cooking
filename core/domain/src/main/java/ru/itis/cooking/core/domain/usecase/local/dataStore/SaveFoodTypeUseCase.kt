package ru.itis.cooking.core.domain.usecase.local.dataStore

import ru.itis.cooking.core.domain.model.FoodType
import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import javax.inject.Inject

typealias SaveFoodTypeBaseUseCase = BaseUseCase<FoodType, Unit>

class SaveFoodTypeUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveFoodTypeBaseUseCase {
    override suspend fun invoke(parameter: FoodType) {
        repository.saveFoodType(parameter)
    }
}
