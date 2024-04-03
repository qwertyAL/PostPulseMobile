package com.example.domain.usecase

import com.example.domain.repository.SavedCookieRepository

class GetTokenUseCase(
    private val savedCookieRepository: SavedCookieRepository
) {

    fun execute(): String = savedCookieRepository.getToken()

}