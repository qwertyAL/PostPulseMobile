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
}