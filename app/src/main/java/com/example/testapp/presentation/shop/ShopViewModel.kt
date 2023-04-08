package com.example.testapp.presentation.shop

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.di.Shop
import com.example.testapp.model.Repository
import com.example.testapp.presentation.PointsCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ShopViewModel @Inject constructor(
    private val repository: Repository,
    @Shop private val pointsCommunication: PointsCommunication,
    private val wallpapersCommunication: WallpapersCommunication
) : ViewModel() {


    fun updatePoints(points: Int) = viewModelScope.launch(Dispatchers.Default) {
        repository.updatePoints(points)
    }

    fun getPoints() = viewModelScope.launch(Dispatchers.Default) {
        repository.getPoints().collect {
            withContext(Dispatchers.Main) {
                pointsCommunication.map(it)
            }
        }
    }

    fun getWallpapers() = viewModelScope.launch(Dispatchers.Default) {
        repository.getWallpapers().collect {
            withContext(Dispatchers.Main) {
                wallpapersCommunication.map(it.map { wallpaper ->
                    if (wallpaper.isOpened)
                        ShopRecyclerItem.OpenWallpaper(wallpaper.id)
                    else
                        ShopRecyclerItem.CloseWallpaper(wallpaper.id, wallpaper.price)
                })
            }
        }
    }

    fun observePoints(owner: LifecycleOwner, observer: Observer<Int>) {
        pointsCommunication.observe(owner, observer)
    }

    fun observeWallpapers(owner: LifecycleOwner, observer: Observer<List<ShopRecyclerItem>>) {
        wallpapersCommunication.observe(owner, observer)
    }

    fun changeItem(imageId: Int) {
        repository.updateWallpapers(imageId)
    }


}