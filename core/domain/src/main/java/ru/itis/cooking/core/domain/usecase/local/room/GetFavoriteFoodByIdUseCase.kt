package ru.itis.cooking.core.domain.usecase.local.room

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFavoriteFoodByIdBaseUseCase = BaseUseCase<Int, Flow<Food?>>

class GetFavoriteFoodByIdUseCase @Inject constructor(
    private val repository: LocalRepository
): GetFavoriteFoodByIdBaseUseCase {
    override suspend fun invoke(parameter: Int): Flow<Food?> {
        return repository.getFoodById(parameter)
    }
}
