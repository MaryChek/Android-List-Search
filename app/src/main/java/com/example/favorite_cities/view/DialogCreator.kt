package com.example.favorite_cities.view

import android.app.Activity
import android.app.Dialog
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

class DialogCreator {
    private var currentDialog: Dialog? = null

    private lateinit var functionAfterPositiveClick: (String) -> Unit
    private var title: String? = null
    private var positiveButton: Int? = null
    private var negativeButton: Int? = null
    private var message: Int? = null

    fun setTitle(newTitle: String?) {
        title = newTitle
    }

    fun setFunctionOnPositive(function: (String) -> Unit) {
        functionAfterPositiveClick = function
    }

    fun setPositiveButtonTitle(@StringRes titleId: Int) {
        positiveButton = titleId
    }

    fun setMessage(@StringRes messageId: Int) {
        message = messageId
    }

    fun setNegativeButtonTitle(@StringRes titleId: Int) {
        negativeButton = titleId
    }

    fun showDialog(activity: Activity, callbackParams: String) {
        currentDialog = activity.let {
            val builderDialog = AlertDialog.Builder(it)
                .setCancelable(true)
            title?.let { title ->
                builderDialog?.setTitle(title)
            }
            message?.let { message ->
                builderDialog?.setMessage(message)
            }
            positiveButton?.let { buttonTitle ->
                builderDialog?.setPositiveButton(buttonTitle) { _, _ ->
                    functionAfterPositiveClick(callbackParams)
                }
            }
            negativeButton?.let { negativeButton ->
                builderDialog?.setNegativeButton(negativeButton) { _, _ -> }
            }
            builderDialog?.show()
        }
    }

    fun onDestroy() =
        currentDialog?.dismiss()
}