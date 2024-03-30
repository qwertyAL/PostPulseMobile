package com.example.prodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {

    private val _login: MutableLiveData<String> = MutableLiveData("sergey")
    val login: LiveData<String> = _login

    private val _id: MutableLiveData<Int> = MutableLiveData(-1)
    val id: LiveData<Int> = _id

    fun loginUser() {
        _id.postValue(0)
    }

    fun logoutUser() {
        _id.postValue(-1)
    }

}