package ru.itis.cooking.onboarding.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.itis.cooking.core.architecture.BaseViewModel
import ru.itis.cooking.core.domain.usecase.base.AllUseCases
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: AllUseCases,
) : BaseViewModel<SplashScreenState, SplashScreenAction, SplashScreenEvent>(
    initialState = SplashScreenState(),
) {

    init {
        accept(SplashScreenEvent.OnInit)
    }

    override fun accept(event: SplashScreenEvent) {
        when (event) {
            SplashScreenEvent.OnInit -> viewModelScope.launch {
                useCases.getUserVisitingUseCase().collectLatest {
                    state { copy(hasUserViewedOnboarding = it) }
                }
            }

            SplashScreenEvent.SetUserVisited -> viewModelScope.launch {
                useCases.saveUserVisitingUseCase(true)
            }
        }
    }
}
