package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class GetListAllSchedulePublicationsUseCase(
    private val apiRepository: ApiRepository
) {

    suspend fun execute(cookie: String, channelId: Int?) = apiRepository.getListAllSchedulePublication(cookie, channelId)

}