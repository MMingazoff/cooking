package com.sdk.domain.domain.usecase.local.dataStore

import com.google.common.truth.Truth.assertThat
import com.sdk.domain.data.repository.FakeLocalRepository
import ru.itis.cooking.core.domain.model.FoodFilters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetFoodFiltersUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetThemeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetUserVisitingUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveFoodTypeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveThemeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveUserVisitingUseCase

class DataStoreUseCasesTest {

    private lateinit var getUserVisitingUseCase: GetUserVisitingUseCase
    private lateinit var saveUserVisitingUseCase: SaveUserVisitingUseCase
    private lateinit var saveFoodTypeUseCase: SaveFoodTypeUseCase
    private lateinit var getFoodFiltersUseCase: GetFoodFiltersUseCase
    private lateinit var saveThemeUseCase: SaveThemeUseCase
    private lateinit var getThemeUseCase: GetThemeUseCase
    private lateinit var fakeLocalRepository: FakeLocalRepository

    @Before
    fun setUp() {
        fakeLocalRepository = FakeLocalRepository()
        getUserVisitingUseCase = GetUserVisitingUseCase(fakeLocalRepository)
        saveUserVisitingUseCase = SaveUserVisitingUseCase(fakeLocalRepository)
        saveFoodTypeUseCase = SaveFoodTypeUseCase(fakeLocalRepository)
        getFoodFiltersUseCase = GetFoodFiltersUseCase(fakeLocalRepository)
        getThemeUseCase = GetThemeUseCase(fakeLocalRepository)
        saveThemeUseCase = SaveThemeUseCase(fakeLocalRepository)
    }

    @Test
    fun `By default user visiting returns false`() = runBlocking {
        val res = getUserVisitingUseCase().first()
        assertThat(res).isFalse()
    }
    @Test
    fun `When save user visiting returns true`() = runBlocking {
        saveUserVisitingUseCase(true)
        val res = getUserVisitingUseCase().first()
        assertThat(res).isTrue()
    }

    @Test
    fun `By default food type returns empty food`() = runBlocking {
        val res = getFoodFiltersUseCase().first()
        assertThat(res).isEqualTo(FoodFilters())
    }
    @Test
    fun `Save food type and check`() = runBlocking {
        saveFoodTypeUseCase(FoodFilters(mIndex = 2, mType = "Pasta", dIndex = 3, dType = "D"))
        val res = getFoodFiltersUseCase().first()
        assertThat(res.mType).contains("P")
    }

    @Test
    fun `By default theme index shouldn't returns null`() = runBlocking {
        val res = getThemeUseCase().first()
        assertThat(res).isNotNull()
    }

    @Test
    fun `By default theme index returns zero`() = runBlocking {
        val res = getThemeUseCase().first()
        assertThat(res).isEqualTo(0)
    }

    @Test
    fun `Save theme index and get it`() = runBlocking {
        saveThemeUseCase(2)
        val res = getThemeUseCase().first()
        assertThat(res).isLessThan(3)
    }
}
