package ru.itis.cooking.core.domain.usecase.local.room

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class SaveFavoriteFoodUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(food: Food) {
        repository.saveFavoriteFood(food)
    }
}
