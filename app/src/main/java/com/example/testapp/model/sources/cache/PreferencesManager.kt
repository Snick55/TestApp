package com.example.testapp.model.sources.cache

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

interface PreferencesManager {

    fun savePoints(points: Int)

    fun getPoints(): Int



    class Base @Inject constructor(private val sharedPreferences: SharedPreferences) : PreferencesManager {

        override fun savePoints(points: Int) {

            sharedPreferences.edit().putInt(POINTS_KEY,points+sharedPreferences.getInt(POINTS_KEY,0)).apply()
        }

        override fun getPoints(): Int {
            Log.d("TAG","PreferencesManager is ${sharedPreferences.getInt(POINTS_KEY,0)}")
          return  sharedPreferences.getInt(POINTS_KEY,0)
        }

        private companion object {
            private const val POINTS_KEY = "POINTS_KEY"
        }

    }
}