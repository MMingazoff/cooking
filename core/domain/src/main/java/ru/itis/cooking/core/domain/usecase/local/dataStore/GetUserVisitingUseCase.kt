package ru.itis.cooking.core.domain.usecase.local.dataStore

import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetUserVisitingBaseUseCase = BaseUseCase<Unit, Flow<Boolean>>

class GetUserVisitingUseCase @Inject constructor(
    private val repository: LocalRepository
) : GetUserVisitingBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<Boolean> {
        return repository.getUserVisiting()
    }
}
