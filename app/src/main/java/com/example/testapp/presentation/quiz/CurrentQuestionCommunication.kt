package com.example.testapp.presentation.quiz

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testapp.model.entities.Question
import com.example.testapp.presentation.Event
import javax.inject.Inject


typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>

interface CurrentQuestionCommunication {

    fun map(question: Event<Question>)

    fun observe(owner: LifecycleOwner, observer: Observer<Event<Question>>)


    class Base @Inject constructor(): CurrentQuestionCommunication{
        private val liveData = MutableLiveEvent<Question>()

        override fun map(question: Event<Question>) {
            liveData.value = question
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<Event<Question>>) {
            liveData.observe(owner, observer)
        }
    }
}