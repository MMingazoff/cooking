package ru.itis.cooking.core.domain.usecase.local.room

import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import javax.inject.Inject

typealias DeleteFavoriteFoodBaseUseCase = BaseUseCase<Int, Unit>

class DeleteFavoriteFoodUseCase @Inject constructor(
    private val repository: LocalRepository
): DeleteFavoriteFoodBaseUseCase {
    override suspend fun invoke(parameter: Int) {
        repository.deleteFood(parameter)
    }
}
