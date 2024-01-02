package ru.itis.cooking.core.domain.usecase.local.dataStore

import ru.itis.cooking.core.domain.model.FoodType
import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFoodTypeBaseUseCase = BaseUseCase<Unit, Flow<FoodType>>

class GetFoodTypeUseCase @Inject constructor(
    private val repository: LocalRepository
): GetFoodTypeBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<FoodType> {
        return repository.getFoodType()
    }
}
