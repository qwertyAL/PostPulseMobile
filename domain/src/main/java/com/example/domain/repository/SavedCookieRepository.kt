package com.example.domain.repository

interface SavedCookieRepository {

    fun setCookie(cookie: String)

    fun getCookie(): String

}