package com.example.prodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginViewModel {

    private var _login: String = ""
    private var _password: String = ""

    fun putLogin(login: String) {
        _login = login
    }

    fun putPassword(password: String) {
        _password = password
    }

    fun loginUser() {

    }

}