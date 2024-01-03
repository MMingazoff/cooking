package ru.itis.cooking.details.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.itis.cooking.core.architecture.BaseViewModel
import ru.itis.cooking.core.domain.usecase.base.AllUseCases
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: AllUseCases,
) : BaseViewModel<DetailsState, DetailsAction, DetailsEvent>(
    initialState = DetailsState()
) {
    override fun accept(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.OnCheckFood -> {
                viewModelScope.launch {
                    useCases.getFavoriteFoodByIdUseCase(event.id).collectLatest {
                        state { copy(isFoodSaved = it != null) }
                    }
                }
            }

            is DetailsEvent.OnUpdateFood -> {
                viewModelScope.launch {
                    if (state.value.isFoodSaved) {
                        useCases.saveFavoriteFoodUseCase(event.food)
                    } else {
                        useCases.deleteFavoriteFoodUseCase(event.food.foodId)
                    }
                }
            }
        }
    }
}
