package com.example.testapp.presentation.quiz

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.Repository
import com.example.testapp.model.entities.Question
import com.example.testapp.presentation.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: Repository,
    private val currentQuestionCommunication: CurrentQuestionCommunication
) : ViewModel() {

    private var currentIndex = 0


    private var list = emptyList<Question>()

    fun getQuestions(filter: String) {
        list = repository.getQuestions(filter)
        currentQuestionCommunication.map(Event(list[0]))
    }

    fun updatePoints(points: Int) = viewModelScope.launch(Dispatchers.Main) {
        repository.updatePoints(points)
    }

    fun nextQuestion() {
        currentIndex++
        if (currentIndex >= list.size) currentIndex = 0
        currentQuestionCommunication.map(Event(list[currentIndex]))
    }

    fun observeQuestion(owner: LifecycleOwner,observer: Observer<Event<Question>>){
        currentQuestionCommunication.observe(owner, observer)
    }

}