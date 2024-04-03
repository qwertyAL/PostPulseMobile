package com.example.domain.usecase

import com.example.domain.repository.SavedCookieRepository

class SetUsernameUseCase(
    private val savedCookieRepository: SavedCookieRepository
) {

    fun execute(name: String) {
        savedCookieRepository.setUsername(name)
    }

}