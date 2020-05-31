package com.rahul.gameapp.utils

import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {

    var isHandled = false
        private set // Allow external read but not write


    fun getDataIfNotHandled(): T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            content
        }
    }

}

