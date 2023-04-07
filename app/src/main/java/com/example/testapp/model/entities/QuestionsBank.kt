package com.example.testapp.model.entities

import com.example.testapp.R
import com.example.testapp.core.ResourceManager
import javax.inject.Inject

interface QuestionsBank {


    fun getAllQuestions(): List<Question>

    class Base @Inject constructor(private val resourceManager: ResourceManager) : QuestionsBank {

        private val list = listOf<Question>(
            Question(
                resourceManager.getString(R.string.question1),
                listOf(
                    Answer("Первое"), Answer("Второе"), Answer("Четвертое"),
                ), "Второе",
                "easy"
            ),
            Question(
                resourceManager.getString(R.string.question2),
                listOf(
                    Answer("Сноуборд"), Answer("Фигурное катание"), Answer("Скейтбординг"),
                ), "Скейтбординг",
                "easy"
            ),
            Question(
                resourceManager.getString(R.string.question3),
                listOf(
                    Answer("5 км"), Answer("10 км"), Answer("42,195 км"),
                ), "42,195 км",
                "easy"
            ),
            Question(
                resourceManager.getString(R.string.question4),
                listOf(
                    Answer("1900 год"), Answer("1912 год"), Answer("1924 год"),
                ), "1924 год",
                "medium"
            ),
            Question(
                resourceManager.getString(R.string.question5),
                listOf(
                    Answer("Тампа-Бэй Лайтнинг"),
                    Answer("Монреаль Канадиенс"),
                    Answer("Нью-Йорк Рейнджерс"),
                ), "Тампа-Бэй Лайтнинг",
                "medium"
            ),
            Question(
                resourceManager.getString(R.string.question6),
                listOf(
                    Answer("68 см"), Answer("74 см"), Answer("80 см"),
                ), "80 см",
                "medium"
            ),
            Question(
                resourceManager.getString(R.string.question7),
                listOf(
                    Answer("9,64 секунды"), Answer("9,68 секунды"), Answer("9,72 секунды"),
                ), "9,68 секунды",
                "hard"
            ),
            Question(
                resourceManager.getString(R.string.question8),
                listOf(
                    Answer("Уимблдон"), Answer("Открытый чемпионат США"), Answer("Australian Open"),
                ), "Открытый чемпионат США",
                "hard"
            ),
            Question(
                resourceManager.getString(R.string.question9),
                listOf(
                    Answer("боевое каратэ"), Answer("кикбоксинг"), Answer("тайский бокс"),
                ), "боевое каратэ",
                "hard"
            ),
        )

        override fun getAllQuestions(): List<Question> {
            return list
        }
    }


}
