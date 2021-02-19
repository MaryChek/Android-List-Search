package com.example.favorite_cities

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AlertDialog

open class DialogCreator {
    private var currentDialog: Dialog? = null

    private var functionAfterPositiveClick: (Int, String) -> Unit = { _, _ -> }

    fun setFunctionOnPositive(function: (Int, String) -> Unit) {
        functionAfterPositiveClick = function
    }

    fun show(
        activity: Activity?,
        title: String,
        message: Int,
        positiveButton: Int,
        negativeButton: Int
    ) {
        currentDialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(positiveButton) { _, _ ->
                    functionAfterPositiveClick(positiveButton, title)
                }
                .setNegativeButton(negativeButton) { _, _ -> }
                .show()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun onDestroy() =
        currentDialog?.dismiss()
}