package com.example.domain.repository

import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel

interface ApiRepository {

    suspend fun getListAllPublication(): List<PublicationModel>

    suspend fun getPublicationById(id: Int): PublicationModel

    suspend fun getListAllDraftPublication(): List<PublicationModel>

    suspend fun getListAllSendPublication(): List<PublicationModel>

    suspend fun getListAllSchedulePublication(): List<PublicationModel>

    suspend fun getListChannels(): List<ChannelModel>

}