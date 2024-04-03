package com.example.domain.repository

import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel
import com.example.domain.model.UserModel

interface ApiRepository {

    suspend fun getListAllPublication(): List<PublicationModel>

    suspend fun getPublicationById(id: Int): PublicationModel

    suspend fun getListAllDraftPublication(cookie: String, channelId: Long?): List<PublicationModel>

    suspend fun getListAllSendPublication(cookie: String, channelId: Long?): List<PublicationModel>

    suspend fun getListAllSchedulePublication(cookie: String, channelId: Long?): List<PublicationModel>

    suspend fun getListChannels(cookie: String): List<ChannelModel>

    suspend fun loginUser(authData: String): UserModel?

    suspend fun sendToken(token: String, cookie: String)
    suspend fun updatePost(
        cookie: String,
        id: Int,
        channelId: Long,
        name: String,
        text: String,
        comment: String,
        scheduledAt: String
    )
}