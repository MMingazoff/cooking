package ru.itis.cooking.core.domain.usecase.local.dataStore

import ru.itis.cooking.core.domain.model.Theme
import ru.itis.cooking.core.domain.repository.LocalRepository
import javax.inject.Inject

class SaveThemeUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(theme: Theme) = repository.saveTheme(theme)
}
