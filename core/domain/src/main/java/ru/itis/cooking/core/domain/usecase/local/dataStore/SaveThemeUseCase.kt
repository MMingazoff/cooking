package ru.itis.cooking.core.domain.usecase.local.dataStore

import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.usecase.base.BaseUseCase
import javax.inject.Inject

typealias SaveThemeBaseUseCase = BaseUseCase<Int, Unit>

class SaveThemeUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveThemeBaseUseCase {
    override suspend fun invoke(parameter: Int) {
        repository.saveTheme(parameter)
    }
}
