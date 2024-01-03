package ru.itis.cooking.core.domain.usecase.local.room

import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class DeleteAllFavoriteFoodsUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke() {
        repository.deleteAllFavoriteFoods()
    }
}
