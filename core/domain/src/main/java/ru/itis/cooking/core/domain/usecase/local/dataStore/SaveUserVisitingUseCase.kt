package ru.itis.cooking.core.domain.usecase.local.dataStore

import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import javax.inject.Inject

typealias SaveUserVisitingBaseUseCase = BaseUseCase<Boolean, Unit>

class SaveUserVisitingUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveUserVisitingBaseUseCase {
    override suspend fun invoke(parameter: Boolean) {
        repository.saveUserVisiting(parameter)
    }
}
