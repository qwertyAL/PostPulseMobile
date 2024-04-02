package com.example.data.source.local

import android.content.Context
import android.util.Log

const val SHARED_PREFERENCES_KEY = "COOKIE_KEY"
const val SHARED_PREFERENCES_NAME = "COOKIE_KEY"


class CookieLocalStore(
    private val context: Context
) {

    fun setCookie(cookie: String) {
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(SHARED_PREFERENCES_KEY, cookie).apply()
    }

    fun getCookie(): String {
        Log.i("TEST_LOGIN", context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(SHARED_PREFERENCES_KEY, "")!!)
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(SHARED_PREFERENCES_KEY, "")!!
    }

}