package com.example.favorite_cities

import android.app.Activity
import android.app.Dialog
import android.icu.util.ULocale
import android.os.Bundle
import android.os.Message
import android.os.ProxyFileDescriptorCallback
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.favorite_cities.presenter.GeneralPresenter

//class DialogCreator(
//    private val nameDialog: String,
//    private val message: Int,
//    private val positiveButton: Int,
//    private val idMessageAfterClickButton: Int,
//    private val cancel: Int,
//    private val functionAfterClick: (String) -> Unit,
//    private val showMessageAfterPositiveClick: (Int, String) -> String?
//) {
//    private lateinit var currentDialog: Dialog
//
//    fun show(activity: Activity?): Dialog {
//        currentDialog = activity?.let {
//            val builder = AlertDialog.Builder(it)
//            builder.setTitle(nameDialog)
//                .setMessage(message)
//                .setCancelable(true)
//                .setPositiveButton(positiveButton) { _, _ ->
////                    Toast.makeText(
////                        activity,
////                        showMessageAfterPositiveClick(idMessageAfterClickButton, nameDialog),
////                        Toast.LENGTH_LONG
////                    ).show()
//                    functionAfterClick(nameDialog)
//                }
//                .setNegativeButton(cancel) { _, _ -> }
//                .show()
//        } ?: throw IllegalStateException("Activity cannot be null")
//        return currentDialog
//    }
//
//    fun onDestroy() {
//        currentDialog.dismiss()
//    }
//}

class DialogCreator {
    private lateinit var currentDialog: Dialog

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

    fun onDestroy() {
        currentDialog.dismiss()
    }
}