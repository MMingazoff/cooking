package ru.itis.cooking.di

import android.content.Context
import androidx.room.Room
import ru.itis.cooking.core.data.local.database.FoodDao
import ru.itis.cooking.core.data.local.database.FoodDatabase
import ru.itis.cooking.core.data.local.manager.DataStoreManager
import ru.itis.cooking.core.data.repository.LocalRepositoryImpl
import ru.itis.cooking.core.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @[Provides Singleton]
    fun provideLocalRepository(manager: DataStoreManager, dao: FoodDao): LocalRepository {
        return LocalRepositoryImpl(manager, dao)
    }

    @[Provides Singleton]
    fun provideFoodDatabase(
        @ApplicationContext context: Context
    ): FoodDatabase {
        return Room.databaseBuilder(
            context,
            FoodDatabase::class.java,
            "Food.db"
        ).build()
    }
    @[Provides Singleton]
    fun providesFoodDao(database: FoodDatabase): FoodDao {
        return database.dao
    }
}
