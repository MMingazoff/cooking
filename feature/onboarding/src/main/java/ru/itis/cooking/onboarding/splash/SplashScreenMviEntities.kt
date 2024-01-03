package ru.itis.cooking.onboarding.splash

data class SplashScreenState(
    val hasUserViewedOnboarding: Boolean = false
)

sealed interface SplashScreenAction

sealed interface SplashScreenEvent {
    object OnInit : SplashScreenEvent
    object SetUserVisited : SplashScreenEvent
}
