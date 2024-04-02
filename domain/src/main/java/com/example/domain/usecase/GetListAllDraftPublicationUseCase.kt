package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class GetListAllDraftPublicationUseCase(
    private val apiRepository: ApiRepository
) {

    suspend fun execute(cookie: String, channelId: Int?) = apiRepository.getListAllDraftPublication(cookie, channelId)

}