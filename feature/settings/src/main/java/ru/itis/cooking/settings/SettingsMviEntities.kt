package ru.itis.cooking.settings

import ru.itis.cooking.core.domain.model.Theme

data class SettingsState(
    val theme: Theme = Theme.AUTOMATIC
)

sealed interface SettingsAction

sealed interface SettingsEvent {
    data class OnChangeTheme(val theme: Theme) : SettingsEvent
}
