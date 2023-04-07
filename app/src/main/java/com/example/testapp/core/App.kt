package com.example.testapp.core

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.testapp.model.Repository
import com.example.testapp.model.entities.QuestionsBank
import com.example.testapp.model.entities.WallpapersBank
import com.example.testapp.model.sources.QuestionsDataSource
import com.example.testapp.model.sources.CacheDataSource
import com.example.testapp.model.sources.WallpaperDataSource
import com.example.testapp.model.sources.cache.PreferencesManager
import com.example.testapp.presentation.quiz.QuizViewModel
import com.example.testapp.presentation.shop.ShopViewModel
import com.example.testapp.presentation.welcome.WelcomeViewModel
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application()