package com.example.testapp.model.entities

data class Question(
     val title: String,
     val answers: List<Answer>,
     val currentAnswer: String,
     val difficulty: String
    )
