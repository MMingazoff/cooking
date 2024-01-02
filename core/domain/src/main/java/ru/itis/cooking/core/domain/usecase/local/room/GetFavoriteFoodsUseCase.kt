package ru.itis.cooking.core.domain.usecase.local.room

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFavoriteFoodsBaseUseCase = BaseUseCase<Unit, Flow<List<Food>>>

class GetFavoriteFoodsUseCase @Inject constructor(
    private val repository: LocalRepository
): GetFavoriteFoodsBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<List<Food>> {
        return repository.getFoodList()
    }
}
