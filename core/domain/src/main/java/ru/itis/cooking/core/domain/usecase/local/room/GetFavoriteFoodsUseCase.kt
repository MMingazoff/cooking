package ru.itis.cooking.core.domain.usecase.local.room

import kotlinx.coroutines.flow.Flow
import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class GetFavoriteFoodsUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    operator fun invoke(): Flow<List<Food>> = repository.getFoodList()
}
