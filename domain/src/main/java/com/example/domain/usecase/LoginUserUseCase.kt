package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class LoginUserUseCase(
    private val apiRepository: ApiRepository
) {

    suspend fun execute(authData: String) = apiRepository.loginUser(authData)

}