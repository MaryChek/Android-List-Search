package com.example.favorite_cities.fragments

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.favorite_cities.R
import com.example.favorite_cities.presenter.PresenterGeneral
import com.example.favorite_cities.presenter.PresenterMain

class DialogFragmentAddRemoveOnFavorite(
    private val presenter: PresenterMain,
    private val nameCity: String
) : DialogFragment() {
    private var message: Int
    private var positiveButton: Int
    private var messageAfterClickButton: Int
    private var functionAfterClick: (String) -> Unit

    init {
        val messageForFavorite = R.string.message_favorite_city
        val messageForUnelected = R.string.message_unelected_city
        val removeButton = R.string.text_button_remove
        val addButton = R.string.text_button_add
        val messageAfterAdd = R.string.message_after_adding
        val messageAfterRemove = R.string.message_after_removal

        message = messageForFavorite
        positiveButton = removeButton
        messageAfterClickButton = messageAfterRemove
        functionAfterClick = presenter::removeCityFromFavorite
        if (presenter is PresenterGeneral
            && !presenter.findCityInFavoriteModel(nameCity)
        ) {
            message = messageForUnelected
            positiveButton = addButton
            messageAfterClickButton = messageAfterAdd
            functionAfterClick = presenter::addCityToFavorite
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(nameCity)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(positiveButton) { _, _ ->
                    Toast.makeText(
                        activity,
                        presenter.getResourceString(messageAfterClickButton, nameCity),
                        Toast.LENGTH_LONG
                    ).show()
                    functionAfterClick(nameCity)
                }
                .setNegativeButton(android.R.string.cancel) { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}