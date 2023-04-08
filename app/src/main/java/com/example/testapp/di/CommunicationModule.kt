package com.example.testapp.di

import com.example.testapp.presentation.PointsCommunication
import com.example.testapp.presentation.quiz.CurrentQuestionCommunication
import com.example.testapp.presentation.shop.ShopPointsCommunication
import com.example.testapp.presentation.shop.WallpapersCommunication
import com.example.testapp.presentation.welcome.WelcomePointsCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Shop

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Welcome

@Module
@InstallIn(SingletonComponent::class)
abstract class CommunicationModule {


    @Binds
    abstract fun bindCurrentQuestionCommunication(
        currentQuestionCommunication  : CurrentQuestionCommunication.Base
    ):CurrentQuestionCommunication

    @Binds
    abstract fun bindWallpapersCommunication(
        wallpapersCommunication  : WallpapersCommunication.Base
    ): WallpapersCommunication

    @Shop
    @Binds
    abstract fun bindShopPointsCommunication(
        shopPointsCommunication  : ShopPointsCommunication
    ):PointsCommunication


    @Welcome
    @Binds
    abstract fun bindWelcomePointsCommunication(
        welcomePointsCommunication  : WelcomePointsCommunication
    ):PointsCommunication


}