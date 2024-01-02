package ru.itis.cooking.core.domain.usecase.local.dataStore

import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetThemeBaseUseCase = BaseUseCase<Unit, Flow<Int>>

class GetThemeUseCase @Inject constructor(
    private val repository: LocalRepository
) : GetThemeBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<Int> {
        return repository.getTheme()
    }
}
