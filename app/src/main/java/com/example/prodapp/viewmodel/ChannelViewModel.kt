package com.example.prodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel
import com.example.domain.usecase.GetCookieFromLocalSourceUseCase
import com.example.domain.usecase.GetListAllDraftPublicationUseCase
import com.example.domain.usecase.GetListAllSchedulePublicationsUseCase
import com.example.domain.usecase.GetListAllSendPublicationsUseCase
import com.example.domain.usecase.GetListChannelsUseCase
import com.example.domain.usecase.UpdatePostUseCase
import com.example.prodapp.utils.PublicationsType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ChannelViewModel(
    private val getListAllDraftPublicationUseCase: GetListAllDraftPublicationUseCase,
    private val getListAllSchedulePublicationsUseCase: GetListAllSchedulePublicationsUseCase,
    private val getListAllSendPublicationsUseCase: GetListAllSendPublicationsUseCase,
    private val getCookieFromLocalSourceUseCase: GetCookieFromLocalSourceUseCase,
    private val getListChannelsUseCase: GetListChannelsUseCase,
    private val updatePostUseCase: UpdatePostUseCase
) : ViewModel() {

    private val _publications: MutableLiveData<MutableList<PublicationModel>> = MutableLiveData()
    val publications: MutableLiveData<MutableList<PublicationModel>> = _publications

    fun loadPublications(channelId: Long, type: Int) {
        val cookie = getCookieFromLocalSourceUseCase.execute()
        if(cookie == "") {
            _publications.postValue(mutableListOf())
        } else {
            when(type) {
                PublicationsType.ALL -> loadAllPublications(cookie = cookie, channelId = channelId)
                PublicationsType.SCHEDULE -> loadSchedule(cookie = cookie, channelId = channelId)
                PublicationsType.DRAFTS -> loadDrafts(cookie = cookie, channelId = channelId)
                PublicationsType.SENT -> loadSent(cookie = cookie, channelId = channelId)
            }
        }
    }

    fun deletePost(post: PublicationModel) {
        val cookie = getCookieFromLocalSourceUseCase.execute()
        if(cookie != "") {
            viewModelScope.launch {
                Log.i("TEST UPDATE", "true1")
                updatePostUseCase.execute(cookie, post.id, post.channelId, post.name, post.text, post.comment, "null")
            }
        }
        _publications.value?.remove(post)
    }

    fun clearPosts() {
        _publications.postValue(emptyList<PublicationModel>().toMutableList())
    }

    private fun loadAllPublications(cookie: String, channelId: Long) {
        if(channelId == -1L) {
            viewModelScope.launch {
                val channels = getListChannelsUseCase.execute(cookie = cookie)
                val list: MutableList<PublicationModel> = mutableListOf()
                channels.forEach {
                    list += getListAllDraftPublicationUseCase.execute(cookie = cookie, channelId = it.id)
                    list += getListAllSchedulePublicationsUseCase.execute(cookie = cookie, channelId = it.id)
                    list += getListAllSendPublicationsUseCase.execute(cookie = cookie, channelId = it.id)
                }
                _publications.postValue(list)
            }
        } else {
            viewModelScope.launch {
                val ld: MutableList<PublicationModel> = getListAllDraftPublicationUseCase.execute(cookie = cookie, channelId = channelId) as MutableList<PublicationModel>
                val lsc = getListAllSchedulePublicationsUseCase.execute(cookie = cookie, channelId = channelId) as MutableList<PublicationModel>
                val ls = getListAllSendPublicationsUseCase.execute(cookie = cookie, channelId = channelId) as MutableList<PublicationModel>
                val l = (ld + lsc + ls) as MutableList<PublicationModel>
                _publications.postValue(l)
            }
        }
    }

    private fun loadDrafts(cookie: String, channelId: Long) {
        if(channelId == -1L) {
            viewModelScope.launch {
                val l: MutableList<PublicationModel> = mutableListOf()
                val channels = getListChannelsUseCase.execute(cookie = cookie)
                channels.forEach {
                    l += getListAllDraftPublicationUseCase.execute(cookie = cookie, channelId = it.id)
                }
                _publications.postValue(l)
            }
        } else {
            viewModelScope.launch {
                _publications.postValue(getListAllDraftPublicationUseCase.execute(cookie = cookie, channelId = channelId) as MutableList<PublicationModel>)
            }
        }
    }

    private fun loadSchedule(cookie: String, channelId: Long) {
        if(channelId == -1L) {
            viewModelScope.launch {
                val l: MutableList<PublicationModel> = mutableListOf()
                val channels = getListChannelsUseCase.execute(cookie = cookie)
                channels.forEach {
                    l += getListAllSchedulePublicationsUseCase.execute(cookie = cookie, channelId = it.id)
                }
                _publications.postValue(l)
            }
        } else {
            viewModelScope.launch {
                _publications.postValue(getListAllSchedulePublicationsUseCase.execute(cookie = cookie, channelId = channelId) as MutableList<PublicationModel>)
            }
        }
    }

    private fun loadSent(cookie: String, channelId: Long) {
        if(channelId == -1L) {
            viewModelScope.launch {
                val l: MutableList<PublicationModel> = mutableListOf()
                val channels = getListChannelsUseCase.execute(cookie = cookie)
                channels.forEach {
                    l += getListAllSendPublicationsUseCase.execute(cookie = cookie, channelId = it.id)
                }
                _publications.postValue(l)
            }
        } else {
            viewModelScope.launch {
                _publications.postValue(getListAllSendPublicationsUseCase.execute(cookie = cookie, channelId = channelId) as MutableList<PublicationModel>)
            }
        }
    }

    private val _channelInfo: MutableLiveData<ChannelModel> = MutableLiveData()
    val channelInfo: LiveData<ChannelModel> = _channelInfo
    fun loadChannelInfo(channelId: Long) {
        Log.i("TEST CHANNEL INFO", channelId.toString())
        if (channelId == -1L) {
            _channelInfo.postValue(ChannelModel(-1L, "Все каналы"))
        } else {
            val cookie = getCookieFromLocalSourceUseCase.execute()
            viewModelScope.launch {
                val listChannels = getListChannelsUseCase.execute(cookie)
                listChannels.forEach {
                    if (it.id == channelId) {
                        _channelInfo.postValue(it)
                    }
                }
            }
        }
    }

}