package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class GetListAllSendPublicationsUseCase(
    private val apiRepository: ApiRepository
) {

    suspend fun execute(cookie: String, channelId: Int?) = apiRepository.getListAllSendPublication(cookie, channelId)

}