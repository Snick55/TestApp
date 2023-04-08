package com.example.testapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import javax.inject.Singleton

interface PointsCommunication {

    fun map(points:Int)

    fun observe(owner: LifecycleOwner,observer: Observer<Int>)


    @Singleton
    abstract class Abstract  :PointsCommunication{

        private val liveData = MutableLiveData<Int>()

        override fun map(points: Int) {
            liveData.value = points
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
            liveData.observe(owner, observer)
        }

    }


}