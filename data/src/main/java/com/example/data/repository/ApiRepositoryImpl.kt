package com.example.data.repository

import com.example.domain.model.PublicationModel
import com.example.data.source.local.ApiLocalSource
import com.example.data.source.remote.ApiRemoteSource
import com.example.domain.model.ChannelModel
import com.example.domain.repository.ApiRepository

class ApiRepositoryImpl(
    private val apiLocalSource: ApiLocalSource,
    private val apiRemoteSource: ApiRemoteSource
): ApiRepository {

    override suspend fun getListAllPublication(): List<PublicationModel> = apiRemoteSource.getListAllPublication()

    override suspend fun getPublicationById(id: Int): PublicationModel {
        val cache = apiLocalSource.getCachePublication(id)
        if(cache != null) return cache
        val res = apiRemoteSource.getPublicationById(id)
        apiLocalSource.cachePublication(res)
        return res
    }

    override suspend fun getListAllDraftPublication(): List<PublicationModel> = apiRemoteSource.getListAllDraftPublication()

    override suspend fun getListAllSendPublication(): List<PublicationModel> = apiRemoteSource.getListAllSendPublication()

    override suspend fun getListAllSchedulePublication(): List<PublicationModel> = apiRemoteSource.getListAllSchedulePublication()

    override suspend fun getListChannels(): List<ChannelModel> = apiRemoteSource.getListChannels()

}