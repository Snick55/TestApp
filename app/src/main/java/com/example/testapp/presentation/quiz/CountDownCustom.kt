package com.example.testapp.presentation.quiz

import android.os.CountDownTimer
import android.widget.TextView

class CountDownCustom(
private val textView: TextView,
private val onTimeFinished: ()-> Unit
) : CountDownTimer(30000, 1000) {

    override fun onTick(time: Long) {
        textView.text =  (time/1000).toString()
    }

    override fun onFinish() {
        onTimeFinished.invoke()
    }
}