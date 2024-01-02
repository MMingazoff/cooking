package ru.itis.cooking.core.navigation

sealed interface Graph {
    val route: String

    sealed class SplashGraph(override val route: String) : Graph {
        object Splash : SplashGraph("splash")
        object Onboarding : SplashGraph("onboarding")
        companion object {
            const val graph = "splash_graph"
        }
    }

    sealed class MainGraph(override val route: String) : Graph {
        companion object {
            const val graph = "main_graph"
        }
    }

    sealed class DetailsGraph(override val route: String) : Graph {
        object Details : DetailsGraph("details")
        companion object {
            const val graph = "details_graph"
        }
    }

    sealed class RootGraph(override val route: String) : Graph {
        companion object {
            const val graph = "root_graph"
        }
    }
}
