package com.example.testonlinestore.utils

import android.content.Context

fun saveUserId(context: Context, userId: String) {
    val sharedPref =
        context.getSharedPreferences(SharedPref.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString(SharedPref.USER_ID_KEY, userId)
        apply()
    }
}

fun removeUserId(context: Context) {
    val sharedPref = context.getSharedPreferences(SharedPref.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString(SharedPref.USER_ID_KEY, SharedPref.DEFAULT_VALUE_USER_ID)
        apply()
    }
}

object SharedPref {
    const val PREFERENCE_FILE_KEY = "PREFERENCE_FILE_KEY"
    const val USER_ID_KEY = "USER_ID_KEY"
    const val DEFAULT_VALUE_USER_ID = ""
}