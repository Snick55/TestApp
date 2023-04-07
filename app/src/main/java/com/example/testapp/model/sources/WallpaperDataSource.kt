package com.example.testapp.model.sources

import com.example.testapp.model.entities.Wallpaper
import com.example.testapp.model.entities.WallpapersBank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface WallpaperDataSource {
    fun getWallpapers(): Flow<List<Wallpaper>>
    fun updateWallpapers(id: Int)


    class Base @Inject constructor(
        private val wallpapersBank: WallpapersBank
    ): WallpaperDataSource{
        override fun getWallpapers(): Flow<List<Wallpaper>> {
            return flow {
                emit(wallpapersBank.getAllWallpapers())
            }
        }

        override fun updateWallpapers(id: Int) {
            wallpapersBank.updateWallpapers(id)
        }
    }
}