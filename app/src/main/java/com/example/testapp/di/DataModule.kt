package com.example.testapp.di

import com.example.testapp.core.ResourceManager
import com.example.testapp.model.Repository
import com.example.testapp.model.entities.QuestionsBank
import com.example.testapp.model.entities.WallpapersBank
import com.example.testapp.model.sources.CacheDataSource
import com.example.testapp.model.sources.QuestionsDataSource
import com.example.testapp.model.sources.WallpaperDataSource
import com.example.testapp.model.sources.cache.PreferencesManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindResourceManager(
        resourceManager: ResourceManager.Base
    ): ResourceManager

    @Binds
    abstract fun bindQuestionsBank(
        questionsBank: QuestionsBank.Base
    ): QuestionsBank

    @Binds
    abstract fun bindQuestionsDataSource(
        questionsDataSource: QuestionsDataSource.Base
    ): QuestionsDataSource

    @Binds
    abstract fun bindPreferencesManager(
        preferencesManager: PreferencesManager.Base
    ): PreferencesManager

    @Binds
    abstract fun bindCacheDataSource(
        cachedDataSource: CacheDataSource.Base
    ): CacheDataSource

    @Binds
    abstract fun bindWallpapersBank(
        wallpapersBank: WallpapersBank.Base
    ): WallpapersBank

    @Binds
    abstract fun bindWallpaperDataSource(
        wallpaperDataSource: WallpaperDataSource.Base
    ): WallpaperDataSource

    @Binds
    abstract fun bindRepository(
        repository: Repository.Base
    ): Repository


}