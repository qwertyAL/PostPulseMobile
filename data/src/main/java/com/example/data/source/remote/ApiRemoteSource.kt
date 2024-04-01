package com.example.data.source.remote

import com.example.data.api.Api
import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel

class ApiRemoteSource(
    private val api: Api
) {

    suspend fun getListAllPublication(): List<PublicationModel> = api.getListAllPublication()

    suspend fun getPublicationById(id: Int): PublicationModel = api.getPublicationById(id)

    suspend fun getListAllDraftPublication(): List<PublicationModel> = api.getListAllDraftPublication()

    suspend fun getListAllSendPublication(): List<PublicationModel> = api.getListAllSendPublication()

    suspend fun getListAllSchedulePublication(): List<PublicationModel> = api.getListAllSchedulePublication()

    suspend fun getListChannels(): List<ChannelModel> = api.getListChannels()

}