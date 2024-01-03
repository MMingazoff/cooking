package com.sdk.domain.domain.usecase.remote

import com.google.common.truth.Truth.assertThat
import com.sdk.domain.data.repository.FakeRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.itis.cooking.core.domain.usecase.remote.GetAllRecipesUseCase

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
        val testFood = fakeRemoteRepository.getAllRecipes(
            query = null,
            type = "alienum",
            diet = "autem",
            number = 2528
        )
        assert(testFood.isSuccess)
    }

    @Test
    fun `Empty query map returns error`() = runBlocking {
        fakeRemoteRepository.isError = true
        val testFood = fakeRemoteRepository.getAllRecipes(
            query = null,
            number = 1911,
            type = "iisque",
            diet = "feugait"
        )
        assertThat(testFood.exceptionOrNull()?.message).isEqualTo("Query map shouldn't be empty!")
    }

    @Test
    fun `Get all food list should be success`() = runBlocking {
        val testFood = fakeRemoteRepository.getAllRecipes(
            query = null,
            number = 5573,
            type = "ultrices",
            diet = "dapibus"
        )
        assertThat(testFood).isEqualTo(Result.success(fakeRemoteRepository.foodList))
    }
}
