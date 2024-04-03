package com.example.prodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel
import com.example.domain.usecase.GetCookieFromLocalSourceUseCase
import com.example.domain.usecase.GetListAllDraftPublicationUseCase
import com.example.domain.usecase.GetListAllPublicationsUseCase
import com.example.domain.usecase.GetListAllSchedulePublicationsUseCase
import com.example.domain.usecase.GetListAllSendPublicationsUseCase
import com.example.domain.usecase.GetListChannelsUseCase
import com.example.domain.usecase.GetUsernameUseCase
import com.example.domain.usecase.UpdatePostUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getListAllPublicationsUseCase: GetListAllPublicationsUseCase,
    private val getListChannelsUseCase: GetListChannelsUseCase,
    private val getCookieFromLocalSourceUseCase: GetCookieFromLocalSourceUseCase,
    private val getListAllDraftPublicationUseCase: GetListAllDraftPublicationUseCase,
    private val getListAllSchedulePublicationsUseCase: GetListAllSchedulePublicationsUseCase,
    private val getListAllSendPublicationsUseCase: GetListAllSendPublicationsUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
    private val getUsernameUseCase: GetUsernameUseCase
): ViewModel() {

    fun getUsername() = getUsernameUseCase.execute()

    private val _publications: MutableLiveData<List<PublicationModel>> = MutableLiveData()
    val publications: LiveData<List<PublicationModel>> = _publications
    fun loadPublications(channelId: Int, tabId: Int) {
        val cookie = getCookieFromLocalSourceUseCase.execute()
        if(cookie == "") {
            _publications.postValue(listOf())
        } else {
            when(tabId) {
                0 -> loadSchedule(cookie, if(channelId == -1) { null } else { channelId })
                1 -> loadDrafts(cookie, if(channelId == -1) { null } else { channelId })
                2 -> loadSent(cookie, if(channelId == -1) { null } else { channelId })
            }
        }
    }

    private fun loadDrafts(cookie: String, channelId: Int?) {
        viewModelScope.launch {
            _publications.postValue(getListAllDraftPublicationUseCase.execute(cookie = cookie, channelId = channelId))
        }
    }

    private fun loadSchedule(cookie: String, channelId: Int?) {
        viewModelScope.launch {
            _publications.postValue(getListAllSchedulePublicationsUseCase.execute(cookie = cookie, channelId = channelId))
        }
    }

    private fun loadSent(cookie: String, channelId: Int?) {
        viewModelScope.launch {
            _publications.postValue(getListAllSendPublicationsUseCase.execute(cookie = cookie, channelId = channelId))
        }
    }

    private val _listChannels: MutableLiveData<List<ChannelModel>> = MutableLiveData()
    val listChannels: LiveData<List<ChannelModel>> = _listChannels
    fun loadListChannels() {
        val cookie = getCookieFromLocalSourceUseCase.execute()
        if(cookie == "") _listChannels.postValue(listOf())
        else {
            viewModelScope.launch {
                _listChannels.postValue(getListChannelsUseCase.execute(cookie))
            }
        }
    }

    fun updatePost(post: PublicationModel) {
        val cookie = getCookieFromLocalSourceUseCase.execute()
        if(cookie != "") {
            viewModelScope.launch {
                updatePostUseCase.execute(cookie, post.id, post.channelId, post.name, post.text, post.comment, "null")
            }
        }
        loadListChannels()
    }

}