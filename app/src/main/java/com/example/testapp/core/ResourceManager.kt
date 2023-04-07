package com.example.testapp.core

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ResourceManager {

    fun getString(@StringRes id: Int): String

    class Base @Inject constructor(@ApplicationContext private val context: Context) : ResourceManager {

        override fun getString(id: Int): String {
            return context.getString(id)
        }
    }
}