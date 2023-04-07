package com.example.testapp.presentation.welcome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
  private val  repository: Repository
): ViewModel() {

    private val _points = MutableLiveData<Int>()
    val points: LiveData<Int> =_points



    fun getPoints()= viewModelScope.launch {
        repository.getPoints().collect{
            Log.d("TAG","value from collect is $it")
            _points.value = it
        }

    }


}