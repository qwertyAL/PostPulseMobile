package com.example.data.repository

import android.util.Log
import com.example.domain.model.PublicationModel
import com.example.data.source.local.ApiLocalSource
import com.example.data.source.remote.ApiRemoteSource
import com.example.domain.model.ChannelModel
import com.example.domain.model.UserModel
import com.example.domain.repository.ApiRepository

class ApiRepositoryImpl(
    private val apiLocalSource: ApiLocalSource,
    private val apiRemoteSource: ApiRemoteSource,
    private val savedCookieRepositoryImpl: SavedCookieRepositoryImpl
): ApiRepository {

    override suspend fun getListAllPublication(): List<PublicationModel> = apiRemoteSource.getListAllPublication()

    override suspend fun getPublicationById(id: Int): PublicationModel {
        val cache = apiLocalSource.getCachePublication(id)
        if(cache != null) return cache
        val res = apiRemoteSource.getPublicationById(id)
        apiLocalSource.cachePublication(res)
        return res
    }

    override suspend fun getListAllDraftPublication(cookie: String, channelId: Long?): List<PublicationModel> = apiRemoteSource.getListAllDraftPublication(cookie, channelId)

    override suspend fun getListAllSendPublication(cookie: String, channelId: Long?): List<PublicationModel> = apiRemoteSource.getListAllSendPublication(cookie, channelId)

    override suspend fun getListAllSchedulePublication(cookie: String, channelId: Long?): List<PublicationModel> = apiRemoteSource.getListAllSchedulePublication(cookie, channelId)

    override suspend fun getListChannels(cookie: String): List<ChannelModel> = apiRemoteSource.getListChannels(cookie)

    override suspend fun loginUser(authData: String): UserModel? {
        val userModel = apiRemoteSource.loginUser(authData)
        if(userModel != null) {
            Log.i("TEST_LOGIN", userModel.cookie)
            savedCookieRepositoryImpl.setCookie(userModel.cookie)
        }
        return userModel
    }

    override suspend fun sendToken(token: String, cookie: String) {
        apiRemoteSource.sendToken(token, cookie)
    }

    override suspend fun updatePost(cookie: String, id: Int, channelId: Long, name: String, text: String, comment: String, scheduledAt: String) {
        apiRemoteSource.updatePost(cookie, id, channelId, name, text, comment, scheduledAt)
    }

}