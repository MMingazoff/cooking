package ru.itis.cooking.core.domain.usecase.remote

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.RemoteRepository
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(
        query: String?,
        number: Int = RECIPES_NUMBER,
        type: String,
        diet: String,
    ): Result<List<Food>> {
        return repository.getAllRecipes(query, number, type, diet)
    }
}

private const val RECIPES_NUMBER = 30
