package ru.itis.cooking.favourites

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.itis.cooking.core.architecture.BaseViewModel
import ru.itis.cooking.core.domain.usecase.base.AllUseCases
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCases: AllUseCases,
) : BaseViewModel<FavoritesState, FavouritesAction, FavoritesEvent>(
    initialState = FavoritesState()
) {
    init {
        accept(FavoritesEvent.GetAllFavoriteFoods)
    }

    override fun accept(event: FavoritesEvent) {
        when (event) {
            FavoritesEvent.DeleteAllFavoriteFoods -> viewModelScope.launch {
                useCases.deleteAllFavoriteFoodsUseCase()
            }

            FavoritesEvent.GetAllFavoriteFoods -> viewModelScope.launch {
                state { copy(isLoading = true) }
                useCases.getFavoriteFoodsUseCase().collectLatest {
                    state { copy(isLoading = false, foodList = it) }
                }
            }
        }
    }
}
