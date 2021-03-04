package com.example.favorite_cities

import android.app.Activity
import android.app.Dialog
import android.app.SharedElementCallback
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import java.util.*
import kotlin.reflect.KFunction1

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
            val builder = AlertDialog.Builder(it)
                .setCancelable(true)
            title?.let { title ->
                builder.setTitle(title)
            }
            message?.let { message ->
                builder.setMessage(message)
            }
            positiveButton?.let { buttonTitle ->
                builder.setPositiveButton(buttonTitle) { _, _ ->
                    functionAfterPositiveClick(callbackParams)
                }
            }
            negativeButton?.let { negativeButton ->
                builder.setNegativeButton(negativeButton) { _, _ -> }
            }
            builder.show()
        }
    }

    fun onDestroy() =
        currentDialog?.dismiss()
}