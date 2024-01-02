package ru.itis.cooking.settings

sealed class SettingsEvent {
    data class OnChangeTheme(val index: Int): SettingsEvent()
}
