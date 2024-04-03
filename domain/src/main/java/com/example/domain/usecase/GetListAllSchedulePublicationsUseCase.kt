package com.example.domain.usecase

import com.example.domain.model.PublicationModel
import com.example.domain.repository.ApiRepository

class GetListAllSchedulePublicationsUseCase(
    private val apiRepository: ApiRepository
) {

    suspend fun execute(cookie: String, channelId: Long?): List<PublicationModel> = apiRepository.getListAllSchedulePublication(cookie, channelId)

}