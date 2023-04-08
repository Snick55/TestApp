package com.example.testapp.presentation.welcome

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.di.Welcome
import com.example.testapp.model.Repository
import com.example.testapp.presentation.PointsCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: Repository,
    @Welcome private val pointsCommunication: PointsCommunication
) : ViewModel() {


    fun getPoints() = viewModelScope.launch(Dispatchers.Default) {
        repository.getPoints().collect {
            withContext(Dispatchers.Main) {
                pointsCommunication.map(it)
            }
        }
    }

    fun observePoints(owner: LifecycleOwner, observer: Observer<Int>) {
        pointsCommunication.observe(owner, observer)
    }


}