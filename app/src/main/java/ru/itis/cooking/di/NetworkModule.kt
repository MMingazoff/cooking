package ru.itis.cooking.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.cooking.core.data.remote.api.FoodService
import ru.itis.cooking.core.data.repository.RemoteRepositoryImpl
import ru.itis.cooking.core.data.util.Constants
import ru.itis.cooking.core.data.util.NetworkHelper
import ru.itis.cooking.core.domain.repository.LocalRepository
import ru.itis.cooking.core.domain.repository.RemoteRepository
import ru.itis.cooking.core.domain.usecase.base.AllUseCases
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetFoodFiltersUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetThemeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.GetUserVisitingUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveFoodTypeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveThemeUseCase
import ru.itis.cooking.core.domain.usecase.local.dataStore.SaveUserVisitingUseCase
import ru.itis.cooking.core.domain.usecase.local.room.DeleteAllFavoriteFoodsUseCase
import ru.itis.cooking.core.domain.usecase.local.room.DeleteFavoriteFoodUseCase
import ru.itis.cooking.core.domain.usecase.local.room.GetFavoriteFoodByIdUseCase
import ru.itis.cooking.core.domain.usecase.local.room.GetFavoriteFoodsUseCase
import ru.itis.cooking.core.domain.usecase.local.room.SaveFavoriteFoodUseCase
import ru.itis.cooking.core.domain.usecase.remote.GetAllRecipesUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    @[Provides Singleton]
    fun provideFoodService(
        okHttpClient: OkHttpClient
    ): FoodService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(FoodService::class.java)
    }

    @[Provides Singleton]
    fun provideRemoteRepository(service: FoodService): RemoteRepository {
        return RemoteRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideAllUseCases(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): AllUseCases {
        return AllUseCases(
            getAllRecipesUseCase = GetAllRecipesUseCase(remoteRepository),
            saveFoodTypeUseCase = SaveFoodTypeUseCase(localRepository),
            getFoodFiltersUseCase = GetFoodFiltersUseCase(localRepository),
            getFavoriteFoodByIdUseCase = GetFavoriteFoodByIdUseCase(localRepository),
            getFavoriteFoodsUseCase = GetFavoriteFoodsUseCase(localRepository),
            saveFavoriteFoodUseCase = SaveFavoriteFoodUseCase(localRepository),
            deleteFavoriteFoodUseCase = DeleteFavoriteFoodUseCase(localRepository),
            deleteAllFavoriteFoodsUseCase = DeleteAllFavoriteFoodsUseCase(localRepository),
            saveThemeUseCase = SaveThemeUseCase(localRepository),
            getThemeUseCase = GetThemeUseCase(localRepository),
            getUserVisitingUseCase = GetUserVisitingUseCase(localRepository),
            saveUserVisitingUseCase = SaveUserVisitingUseCase(localRepository)
        )
    }

    @[Provides Singleton]
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }
}
