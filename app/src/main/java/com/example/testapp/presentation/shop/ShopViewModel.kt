package com.example.testapp.presentation.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShopViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _points = MutableLiveData<Int>()
    val points: LiveData<Int> = _points

    private val _wallpapers = MutableLiveData<List<ShopRecyclerItem>>()
    val wallpapers: LiveData<List<ShopRecyclerItem>> = _wallpapers

    fun updatePoints(points: Int) = viewModelScope.launch {
        repository.updatePoints(points)
    }

    fun getPoints() = viewModelScope.launch {
        repository.getPoints().collect {
            _points.value = it
        }
    }

    fun getWallpapers() = viewModelScope.launch {
        repository.getWallpapers().collect {
            _wallpapers.value = it.map { wallpaper ->
                if (wallpaper.isOpened)
                    ShopRecyclerItem.OpenWallpaper(wallpaper.id)
                else
                    ShopRecyclerItem.CloseWallpaper(wallpaper.id, wallpaper.price)
            }
        }
    }

    fun changeItem(imageId: Int) {
        repository.updateWallpapers(imageId)
    }


}