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

    suspend fun getListAllDraftPublication(cookie: String, channelId: Long?): List<PublicationModel> = api.getListAllDraftPublication(cookie, channelId)

    suspend fun getListAllSendPublication(cookie: String, channelId: Long?): List<PublicationModel> = api.getListAllSendPublication(cookie, channelId)

    suspend fun getListAllSchedulePublication(cookie: String, channelId: Long?): List<PublicationModel> = api.getListAllSchedulePublication(cookie, channelId)

    suspend fun getListChannels(cookie: String): List<ChannelModel> = api.getListChannels(cookie)

    suspend fun loginUser(authData: String): UserModel? = api.loginUser(authData)

    suspend fun sendToken(token: String, cookie: String) {
        api.sendMobileToken(token, cookie)
    }

    suspend fun updatePost(cookie: String, id: Int, channelId: Long, name: String, text: String, comment: String, scheduledAt: String?) {
        api.updatePost(cookie, id, channelId, name, text, comment, scheduledAt)
    }

}