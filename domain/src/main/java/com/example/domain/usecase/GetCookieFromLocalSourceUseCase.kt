package com.example.domain.usecase

import com.example.domain.repository.SavedCookieRepository

class GetCookieFromLocalSourceUseCase(
    private val cookieRepository: SavedCookieRepository
) {



    fun execute() = cookieRepository.getCookie()

}