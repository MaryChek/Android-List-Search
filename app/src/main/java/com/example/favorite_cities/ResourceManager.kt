package com.example.favorite_cities

import com.example.favorite_cities.R

class ResourceManager {
    fun string(nameResourcesString: String): Int? =
        when (nameResourcesString) {
            "message_favorite_city" -> R.string.message_favorite_city
            "message_unelected_city" -> R.string.message_unelected_city
            "message_after_adding" -> R.string.message_after_adding
            "message_after_removal" -> R.string.message_after_removal
            "text_button_add" -> R.string.text_button_add
            "text_button_remove" -> R.string.text_button_remove
            "button_cancel" -> R.string.button_cancel
            else -> null
        }
}