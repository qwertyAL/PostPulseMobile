package com.example.domain.usecase

import com.example.domain.repository.SavedCookieRepository

class SetCookieToLocalSourceUseCase(
    private val cookieRepository: SavedCookieRepository
) {

    fun set(cookie: String) {
        cookieRepository.setCookie(cookie)
    }

}