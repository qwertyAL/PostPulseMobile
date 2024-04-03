package com.example.domain.repository

interface SavedCookieRepository {

    fun setCookie(cookie: String)

    fun getCookie(): String

    fun setToken(token: String)

    fun getToken(): String

    fun setUsername(name: String)
    fun getUsername(): String
}