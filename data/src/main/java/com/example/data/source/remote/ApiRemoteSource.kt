package com.example.data.source.remote

import com.example.data.api.Api
import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel
import com.example.domain.model.UserModel

class ApiRemoteSource(
    private val api: Api
) {

    suspend fun getListAllPublication(): List<PublicationModel> = api.getListAllPublication()

    suspend fun getPublicationById(id: Int): PublicationModel = api.getPublicationById(id)

    suspend fun getListAllDraftPublication(cookie: String, channelId: Int?): List<PublicationModel> = api.getListAllDraftPublication(cookie, channelId)

    suspend fun getListAllSendPublication(cookie: String, channelId: Int?): List<PublicationModel> = api.getListAllSendPublication(cookie, channelId)

    suspend fun getListAllSchedulePublication(cookie: String, channelId: Int?): List<PublicationModel> = api.getListAllSchedulePublication(cookie, channelId)

    suspend fun getListChannels(cookie: String): List<ChannelModel> = api.getListChannels(cookie)

    suspend fun loginUser(authData: String): UserModel? = api.loginUser(authData)

}