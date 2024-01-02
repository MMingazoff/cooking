package ru.itis.cooking.core.domain.usecase.remote

import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.repository.RemoteRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import ru.itis.cooking.core.domain.util.DataResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetAllRecipesBaseUseCase = BaseUseCase<Map<String, String>, Flow<DataResult<List<Food>>>>

class GetAllRecipesUseCase @Inject constructor(
    private val repository: RemoteRepository
): GetAllRecipesBaseUseCase {
    override suspend fun invoke(parameter: Map<String, String>): Flow<DataResult<List<Food>>> {
        return repository.getAllRecipes(parameter)
    }
}
