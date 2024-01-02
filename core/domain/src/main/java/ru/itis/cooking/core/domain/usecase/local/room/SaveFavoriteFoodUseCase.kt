package ru.itis.cooking.core.domain.usecase.local.room

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import javax.inject.Inject

typealias SaveFavoriteFoodBaseUseCase = BaseUseCase<Food, Unit>

class SaveFavoriteFoodUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveFavoriteFoodBaseUseCase {
    override suspend fun invoke(parameter: Food) {
        repository.saveFavoriteFood(parameter)
    }
}
