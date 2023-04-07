package com.example.testapp.presentation.shop

import androidx.annotation.DrawableRes

interface ShopRecyclerItem {
    class CloseWallpaper(@DrawableRes val image: Int, val price: Int) : ShopRecyclerItem
    class OpenWallpaper(@DrawableRes val image: Int) : ShopRecyclerItem
}