package com.example.data.source.local

import android.content.Context
import android.util.Log
import org.json.JSONObject

const val SHARED_PREFERENCES_KEY1 = "COOKIE_KEY1"
const val SHARED_PREFERENCES_KEY2 = "COOKIE_KEY2"
const val SHARED_PREFERENCES_KEY3 = "COOKIE_KEY3"
const val SHARED_PREFERENCES_NAME = "COOKIE_KEY"


class CookieLocalStore(
    private val context: Context
) {

    fun setCookie(cookie: String) {
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(SHARED_PREFERENCES_KEY1, cookie).apply()
    }

    fun getCookie(): String {
        Log.i("TEST_LOGIN", context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(SHARED_PREFERENCES_KEY1, "")!!)
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(SHARED_PREFERENCES_KEY1, "")!!
    }

    fun setToken(token: String) {
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(SHARED_PREFERENCES_KEY2, token).apply()
    }

    fun getToken(): String {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(SHARED_PREFERENCES_KEY2, "")!!
    }

    fun getUsername(): String {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(SHARED_PREFERENCES_KEY3, "Отсутствует")!!
    }

    fun setUsername(name: String) {
//        Log.i("")
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(SHARED_PREFERENCES_KEY3, JSONObject(name).getString("username")).apply()
    }

}