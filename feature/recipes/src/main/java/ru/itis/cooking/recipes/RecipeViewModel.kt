package ru.itis.cooking.recipes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.itis.cooking.core.data.util.Constants
import ru.itis.cooking.core.data.util.NetworkHelper
import ru.itis.cooking.core.domain.model.FoodType
import ru.itis.cooking.core.domain.usecase.base.AllUseCases
import ru.itis.cooking.core.domain.util.DataResult
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val useCases: AllUseCases,
    private val helper: NetworkHelper
) : ViewModel() {
    private val _state: MutableState<RecipesState> = mutableStateOf(RecipesState())
    val state: State<RecipesState> get() = _state
    private val _foodState: MutableState<FoodType> = mutableStateOf(FoodType())
    val foodState: State<FoodType> get() = _foodState
    var firstTime = false

    init {
        getFoodState()
        firstTime = true
    }

    private fun getFoodState() {
        viewModelScope.launch {
            useCases.getFoodTypeUseCase.invoke(Unit).collectLatest {
                _foodState.value = it
            }
        }
    }

    fun onEvent(event: RecipeEvent) {
        when (event) {
            is RecipeEvent.GetAllRecipes -> {
                getRecipes(query = null,event.foodType.mType, event.foodType.dType)
            }
            is RecipeEvent.OnSearchFood -> {
                viewModelScope.launch {
                    getRecipes(event.query,_foodState.value.mType,_foodState.value.dType)
                }
            }
            is RecipeEvent.OnSaveFoodType -> {
                viewModelScope.launch(Dispatchers.IO) {
                    useCases.saveFoodTypeUseCase(event.foodType)
                }
            }
            is RecipeEvent.OnApplyClicked -> {
                viewModelScope.launch {
                    getRecipes(null,_foodState.value.mType,_foodState.value.dType)
                }
            }
        }
    }

    private fun getRecipes(
        query: String? = null,
        type: String,
        diet: String
    ) {
        if (helper.isNetworkConnected()) {
            viewModelScope.launch {
                useCases.getAllRecipesUseCase(
                    getQueries(
                        query = query,
                        type = type,
                        diet = diet
                    )
                ).collectLatest { response ->
                    when (response) {
                        is DataResult.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }
                        is DataResult.Error -> {
                            _state.value =
                                _state.value.copy(isLoading = false, error = response.message)
                        }
                        is DataResult.Success -> {
                            _state.value =
                                _state.value.copy(isLoading = false, success = response.data)
                        }
                    }
                }
            }
        } else {
            _state.value = _state.value.copy(isLoading = false, error = "No internet connection")
        }
    }

    private fun getQueries(
        query: String?,
        type: String,
        diet: String
    ): HashMap<String, String> {
        val map = HashMap<String, String>()
        query?.let {
            map["query"] = it
        }
        map["number"] = "30"
        map["apiKey"] = Constants.API_KEY
        map["addRecipeInformation"] = "true"
        map["fillIngredients"] = "true"
        map["type"] = type.lowercase()
        map["diet"] = diet.lowercase()
        return map
    }

    override fun onCleared() {
        super.onCleared()
        firstTime = false
    }
}
