package ru.itis.cooking.recipes

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.itis.cooking.core.architecture.BaseViewModel
import ru.itis.cooking.core.data.util.NetworkHelper
import ru.itis.cooking.core.domain.usecase.base.AllUseCases
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val useCases: AllUseCases,
    private val helper: NetworkHelper
) : BaseViewModel<RecipesState, RecipesAction, RecipesEvent>(
    initialState = RecipesState()
) {
    init {
        accept(RecipesEvent.GetAllRecipes)
    }

    override fun accept(event: RecipesEvent) {
        when (event) {
            RecipesEvent.GetAllRecipes -> {
                viewModelScope.launch {
                    useCases.getFoodFiltersUseCase().collectLatest {
                        state { copy(foodFilters = it) }
                        getRecipes(query = null)
                    }
                }
            }

            is RecipesEvent.OnSearchFood -> {
                viewModelScope.launch {
                    getRecipes(event.query)
                }
            }

            is RecipesEvent.OnSaveFoodType -> {
                viewModelScope.launch {
                    useCases.saveFoodTypeUseCase(event.foodFilters)
                }
            }
        }
    }

    private fun getRecipes(query: String? = null) {
        if (!helper.isNetworkConnected()) {
            state { copy(errorMessage = "No internet connection") }
            return
        }
        state { copy(isLoading = true) }
        viewModelScope.launch {
            useCases.getAllRecipesUseCase(
                query = query,
                type = state.value.foodFilters.mType.lowercase(),
                diet = state.value.foodFilters.dType.lowercase(),
            ).fold(
                onSuccess = {
                    state { copy(isLoading = false, foodList = it) }
                },
                onFailure = {
                    state { copy(isLoading = false, errorMessage = it.message.orEmpty()) }
                }
            )
        }
    }
}
