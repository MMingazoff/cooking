package ru.itis.cooking.core.domain.usecase.local.dataStore

import kotlinx.coroutines.flow.Flow
import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class GetUserVisitingUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.getUserVisiting()
    }
}
