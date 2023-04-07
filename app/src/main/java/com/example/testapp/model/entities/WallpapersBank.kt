package com.example.testapp.model.entities

import com.example.testapp.R
import javax.inject.Inject

interface WallpapersBank {

    fun getAllWallpapers(): List<Wallpaper>

    fun updateWallpapers(id: Int)


    class Base @Inject constructor(): WallpapersBank {

        private val list = listOf<Wallpaper>(
            Wallpaper(R.drawable.wallpaper1,5),
            Wallpaper(R.drawable.wallpaper2,10),
            Wallpaper(R.drawable.wallpaper3,7),
            Wallpaper(R.drawable.wallpaper4,3),
            Wallpaper(R.drawable.wallpaper5,15),

        )
        override fun getAllWallpapers(): List<Wallpaper> {
            return list
        }

        override fun updateWallpapers(id: Int) {
            list.forEach {
                if (it.id == id) it.isOpened = !it.isOpened
            }
        }
    }

}