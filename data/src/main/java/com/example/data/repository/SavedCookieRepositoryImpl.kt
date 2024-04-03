package com.example.data.repository

import com.example.data.source.local.CookieLocalStore
import com.example.domain.repository.SavedCookieRepository

class SavedCookieRepositoryImpl (
    private val cookieLocalStore: CookieLocalStore
): SavedCookieRepository {
    override fun setCookie(cookie: String) {
        cookieLocalStore.setCookie(cookie)
    }

    override fun getCookie(): String = cookieLocalStore.getCookie()
    override fun setToken(token: String) {
        cookieLocalStore.setToken(token)
    }

    override fun getToken(): String = cookieLocalStore.getToken()

    override fun setUsername(name: String) {
        cookieLocalStore.setUsername(name)
    }

    override fun getUsername() = cookieLocalStore.getUsername()
}