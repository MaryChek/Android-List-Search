package com.example.favorite_cities

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AlertDialog

open class DialogCreator {
    private var currentDialog: Dialog? = null

    private var functionAfterPositiveClick: (Int, String?) -> Unit = { _, _ -> }
    private var title: String? = null
    private var positiveButtonAdd: Int? = null
    private var positiveButtonRemove: Int? = null
    private var negativeButton: Int? = null
    private var messageBeforeAdding: Int? = null
    private var messageBeforeRemoving: Int? = null

    open fun setTitle(newTitle: String?) {
        title = newTitle
    }

    open fun setFunctionOnPositive(function: (Int, String?) -> Unit) {
        functionAfterPositiveClick = function
    }

    open fun setButtonAddTitle(titleId: Int) {
        positiveButtonAdd = titleId
    }

    open fun setMessageBeforeAdding(messageId: Int) {
        messageBeforeAdding = messageId
    }

    open fun setButtonRemoveTitle(titleId: Int) {
        positiveButtonRemove = titleId
    }

    open fun setMessageBeforeRemoving(messageId: Int) {
        messageBeforeRemoving = messageId
    }

    open fun setNegativeButtonTitle(titleId: Int) {
        negativeButton = titleId
    }

    fun showDialogAdding(activity: Activity?) {
        currentDialog = activity?.let {
            val builder = AlertDialog.Builder(it)
                .setCancelable(true)
            title?.let { title ->
                builder.setTitle(title)
            }
            messageBeforeAdding?.let { message ->
                builder.setMessage(message)
            }
            positiveButtonAdd?.let { buttonTitle ->
                builder.setPositiveButton(buttonTitle) { _, _ ->
                    functionAfterPositiveClick(buttonTitle, title)
                }
            }
            negativeButton?.let { negativeButton ->
                builder.setNegativeButton(negativeButton) { _, _ -> }
            }
            builder.show()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun showDialogRemoving(activity: Activity?) {
        currentDialog = activity?.let {
            val builder = AlertDialog.Builder(it)
                .setCancelable(true)
            title?.let { title ->
                builder.setTitle(title)
            }
            messageBeforeRemoving?.let { message ->
                builder.setMessage(message)
            }
            positiveButtonRemove?.let { buttonTitle ->
                builder.setPositiveButton(buttonTitle) { _, _ ->
                    functionAfterPositiveClick(buttonTitle, title)
                }
            }
            negativeButton?.let { negativeButton ->
                builder.setNegativeButton(negativeButton) { _, _ -> }
            }
            builder.show()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    open fun onDestroy() =
        currentDialog?.dismiss()
}