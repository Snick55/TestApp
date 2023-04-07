package com.example.testapp.presentation.shop

import android.app.AlertDialog
import com.example.testapp.R

object DialogManager {

    fun showAlert(builder: AlertDialog.Builder, block: () -> Unit){
        val dialog = builder.create()
        dialog.setTitle(R.string.dialog_title)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"Да"){ _, _ ->
            block.invoke()
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Нет"){ _, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }

}