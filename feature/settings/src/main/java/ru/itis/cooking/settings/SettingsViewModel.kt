package ru.itis.cooking.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.itis.cooking.core.architecture.BaseViewModel
import ru.itis.cooking.core.domain.usecase.base.AllUseCases
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: AllUseCases
) : BaseViewModel<SettingsState, SettingsAction, SettingsEvent>(
    initialState = SettingsState()
) {
    init {
        viewModelScope.launch {
            useCases.getThemeUseCase().collectLatest {
                state { copy(theme = it)}
            }
        }
    }

    override fun accept(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnChangeTheme -> viewModelScope.launch {
                useCases.saveThemeUseCase(event.theme)
            }
        }
    }
}
