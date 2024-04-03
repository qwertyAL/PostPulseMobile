package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class SendTokenUseCase(
    private val apiRepository: ApiRepository
) {

    suspend fun execute(token: String, cookie: String) {
        apiRepository.sendToken(token, cookie)
    }

}