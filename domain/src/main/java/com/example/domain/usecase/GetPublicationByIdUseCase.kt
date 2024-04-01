package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class GetPublicationByIdUseCase(
    private val apiRepository: ApiRepository
) {

    suspend fun execute(id: Int) = apiRepository.getPublicationById(id)

}