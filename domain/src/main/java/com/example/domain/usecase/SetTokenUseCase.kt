package com.example.domain.usecase

import com.example.domain.repository.SavedCookieRepository

class SetTokenUseCase(
    private val savedCookieRepository: SavedCookieRepository
) {

    fun execute(token: String) {
        savedCookieRepository.setToken(token)
    }

}