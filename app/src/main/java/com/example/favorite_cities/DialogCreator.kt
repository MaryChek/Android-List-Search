package com.example.favorite_cities

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.favorite_cities.presenter.GeneralPresenter

class DialogCreator(
    private val nameDialog: String,
    private val message: Int,
    private val positiveButton: Int,
    private val messageAfterClickButton: Int,
    private val cancel: Int,
    private val functionAfterClick: (String) -> Unit,
    private val showMessageAfterPositiveClick: (Int, String) -> String?
) {

    fun show(activity: Activity?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(nameDialog)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(positiveButton) { _, _ ->
                    Toast.makeText(
                        activity,
                        showMessageAfterPositiveClick(messageAfterClickButton, nameDialog),
                        Toast.LENGTH_LONG
                    ).show()
                    functionAfterClick(nameDialog)
                }.setNegativeButton(cancel) { _, _ -> }
            builder.show()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}