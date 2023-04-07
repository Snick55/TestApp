package com.example.testapp.presentation.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.Repository
import com.example.testapp.model.entities.Question
import com.example.testapp.presentation.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>
typealias LiveEvent<T> = LiveData<Event<T>>


@HiltViewModel
class QuizViewModel @Inject constructor(
  private val  repository: Repository
): ViewModel() {

    private var currentIndex = 0

    private val _currentQuestion = MutableLiveEvent<Question>()
     val currentQuestion:LiveEvent<Question> =  _currentQuestion

    private var list = emptyList<Question>()

    fun getQuestions(filter: String){
       list = repository.getQuestions(filter)
        _currentQuestion.value = Event(list[0])
    }

    fun updatePoints(points: Int) = viewModelScope.launch {
        repository.updatePoints(points)
    }

    fun nextQuestion(){
        currentIndex++
        if (currentIndex >= list.size) currentIndex = 0
        _currentQuestion.value = Event(list[currentIndex])
    }

}