package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class UpdatePostUseCase(
    private val apiRepository: ApiRepository
) {

    suspend fun execute(
        cookie: String,
        id: Int,
        channelId: Long,
        name: String,
        text: String,
        comment: String,
        scheduledAt: String) {
        apiRepository.updatePost(cookie, id, channelId, name, text, comment, scheduledAt)
    }

}