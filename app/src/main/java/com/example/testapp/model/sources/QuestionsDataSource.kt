package com.example.testapp.model.sources

import com.example.testapp.model.entities.Question
import com.example.testapp.model.entities.QuestionsBank
import javax.inject.Inject

interface QuestionsDataSource {

    fun getQuestions(filter: String): List<Question>

    class Base @Inject constructor(private val questionsBank: QuestionsBank):QuestionsDataSource{


        override fun getQuestions(filter: String): List<Question> {
            return questionsBank.getAllQuestions().filter {
                it.difficulty == filter
            }.shuffled()
        }
    }
}