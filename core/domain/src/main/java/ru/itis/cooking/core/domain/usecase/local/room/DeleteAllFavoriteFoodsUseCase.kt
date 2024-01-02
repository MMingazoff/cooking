package ru.itis.cooking.core.domain.usecase.local.room

import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import javax.inject.Inject

typealias DeleteAllFavoriteFoodsBaseUseCase = BaseUseCase<Unit, Unit>

class DeleteAllFavoriteFoodsUseCase @Inject constructor(
    private val repository: LocalRepository
) : DeleteAllFavoriteFoodsBaseUseCase {
    override suspend fun invoke(parameter: Unit) {
        repository.deleteAllFavoriteFoods()
    }
}
