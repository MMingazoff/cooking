package com.sdk.domain.domain.usecase.remote

import com.google.common.truth.Truth.assertThat
import com.sdk.domain.data.repository.FakeRemoteRepository
import ru.itis.cooking.core.domain.model.Food
import ru.itis.cooking.core.domain.usecase.remote.GetAllRecipesUseCase
import ru.itis.cooking.core.domain.util.DataResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllRecipesUseCaseTest {
    private lateinit var getAllRecipesUseCase: GetAllRecipesUseCase
    private lateinit var fakeRemoteRepository: FakeRemoteRepository

    @Before
    fun setUp() {
        fakeRemoteRepository = FakeRemoteRepository()
        getAllRecipesUseCase = GetAllRecipesUseCase(fakeRemoteRepository)
    }
    @Test
    fun `Get all food list won't be error`() = runBlocking {
        val testFood = fakeRemoteRepository.getAllRecipes(hashMapOf("test1" to "test2")).first()
        assertThat(testFood).isNotEqualTo(DataResult.Error(""))
    }
    @Test
    fun `Empty query map returns error`() = runBlocking {
        val testFood = fakeRemoteRepository.getAllRecipes(hashMapOf()).first()
        assertThat(testFood).isEqualTo(DataResult.Error("Query map shouldn't be empty!"))
    }
    @Test
    fun `Get all food list should be success`() = runBlocking {
        val testFood = fakeRemoteRepository.getAllRecipes(hashMapOf("test1" to "test2")).first()
        assertThat(testFood).isEqualTo(DataResult.Success<List<Food>>(fakeRemoteRepository.foodList))
    }
}
