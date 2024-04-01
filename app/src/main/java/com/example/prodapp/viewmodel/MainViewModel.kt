package com.example.prodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel
import com.example.domain.usecase.GetListAllPublicationsUseCase
import com.example.domain.usecase.GetListChannelsUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getListAllPublicationsUseCase: GetListAllPublicationsUseCase,
    private val getListChannelsUseCase: GetListChannelsUseCase
): ViewModel() {

    private val _publications: MutableLiveData<List<PublicationModel>> = MutableLiveData()
    val publications: LiveData<List<PublicationModel>> = _publications
    fun loadAllPublication(type: Int) {
        when(type) {
            0 -> _publications.postValue(listAllSchedulePublications.value)
            1 -> _publications.postValue(listAllDraftPublications.value)
            2 -> _publications.postValue(listAllSentPublications.value)
        }
    }

    private val _listChannels: MutableLiveData<List<ChannelModel>> = MutableLiveData()
    val listChannels: LiveData<List<ChannelModel>> = _listChannels
    fun loadListChannels() {
        viewModelScope.launch {
            _listChannels.postValue(getListChannelsUseCase.execute())
        }
    }

    private val _listAllDraftPublications: MutableLiveData<List<PublicationModel>> = MutableLiveData()
    val listAllDraftPublications: LiveData<List<PublicationModel>> = _listAllDraftPublications
    fun loadListAllDraftPublications() {
        _listAllDraftPublications.postValue(listOf(
            PublicationModel(
            id = 0,
            title = "черновик",
            text = "Черновой",
            time = null
        )
        ))
    }

    private val _listAllSchedulePublications: MutableLiveData<List<PublicationModel>> = MutableLiveData()
    val listAllSchedulePublications: LiveData<List<PublicationModel>> = _listAllSchedulePublications
    fun loadListAllSchedulePublications() {
        _listAllSchedulePublications.postValue(listOf(
            PublicationModel(
            id = 0,
            title = "запланированный",
            text = "бяка",
            time = 100
        )
        ))
    }

    private val _listAllSentPublications: MutableLiveData<List<PublicationModel>> = MutableLiveData()
    val listAllSentPublications: LiveData<List<PublicationModel>> = _listAllSentPublications
    fun loadListAllSentPublications() {
        _listAllSentPublications.postValue(listOf(
            PublicationModel(
                id = 0,
                title = "отправленный",
                text = "бука",
                time = 50
            )
        ))
    }

}