package ru.itis.cooking.core.domain.usecase.local.dataStore

import kotlinx.coroutines.flow.Flow
import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    operator fun invoke(): Flow<Int> = repository.getTheme()
}
