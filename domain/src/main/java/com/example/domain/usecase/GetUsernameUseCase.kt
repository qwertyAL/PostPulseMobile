package com.example.domain.usecase

import com.example.domain.repository.SavedCookieRepository

class GetUsernameUseCase(
    private val savedCookieRepository: SavedCookieRepository
) {

    fun execute() = savedCookieRepository.getUsername()

}