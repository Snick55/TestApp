package com.example.testapp.model.entities

import androidx.annotation.DrawableRes

data class Wallpaper(
    @DrawableRes val id: Int,
    val price: Int,
    var isOpened: Boolean = false,
)
