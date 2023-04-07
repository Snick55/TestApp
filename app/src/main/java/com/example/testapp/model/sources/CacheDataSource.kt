package com.example.testapp.model.sources

import com.example.testapp.model.sources.cache.PreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CacheDataSource {

   suspend fun updatePoints(point: Int)

    fun getPoints(): Flow<Int>

   class Base @Inject constructor(private val preferencesManager: PreferencesManager): CacheDataSource {

      override suspend fun updatePoints(point: Int) {
         preferencesManager.savePoints(point)
      }


      override  fun getPoints(): Flow<Int> {
         return flow {
           emit(preferencesManager.getPoints())
         }
      }
   }


}