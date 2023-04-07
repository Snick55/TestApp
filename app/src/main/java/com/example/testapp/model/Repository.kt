package com.example.testapp.model

import com.example.testapp.model.entities.Question
import com.example.testapp.model.entities.Wallpaper
import com.example.testapp.model.sources.CacheDataSource
import com.example.testapp.model.sources.QuestionsDataSource
import com.example.testapp.model.sources.WallpaperDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Repository {

    fun getQuestions(filter: String): List<Question>

    suspend fun updatePoints(points: Int)

     fun getPoints(): Flow<Int>

     fun getWallpapers(): Flow<List<Wallpaper>>

     fun updateWallpapers(id: Int)


    class Base @Inject constructor(
     private val questionsDataSource: QuestionsDataSource,
     private val cachedDataSource: CacheDataSource,
     private val wallpaperDataSource: WallpaperDataSource
    ): Repository{

        override fun getQuestions(filter: String): List<Question> {
           return questionsDataSource.getQuestions(filter)
        }

        override suspend fun updatePoints(points: Int) {
            cachedDataSource.updatePoints(points)
        }

        override  fun getPoints(): Flow<Int> {
            return cachedDataSource.getPoints()
        }

        override fun getWallpapers(): Flow<List<Wallpaper>> {
          return  wallpaperDataSource.getWallpapers()
        }

        override fun updateWallpapers(id: Int) {
            wallpaperDataSource.updateWallpapers(id)
        }
    }

}