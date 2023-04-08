package com.example.testapp.presentation.shop

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import javax.inject.Inject

interface WallpapersCommunication {

    fun map(wallpapers: List<ShopRecyclerItem>)

    fun observe(owner: LifecycleOwner, observer: Observer<List<ShopRecyclerItem>>)

    class Base @Inject constructor(): WallpapersCommunication{
        private val liveData = MutableLiveData<List<ShopRecyclerItem>>()

        override fun map(wallpapers: List<ShopRecyclerItem>) {
            liveData.value = wallpapers
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<ShopRecyclerItem>>) {
            liveData.observe(owner, observer)
        }
    }
}