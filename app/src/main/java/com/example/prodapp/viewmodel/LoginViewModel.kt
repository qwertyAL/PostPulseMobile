package com.example.prodapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.domain.usecase.GetTokenUseCase
import com.example.domain.usecase.LoginUserUseCase
import com.example.domain.usecase.SendTokenUseCase
import com.example.domain.usecase.SetUsernameUseCase
import kotlinx.coroutines.launch

class LoginViewModel (
    private val loginUserUseCase: LoginUserUseCase,
    private val sendTokenUseCase: SendTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val setUsernameUseCase: SetUsernameUseCase
): ViewModel() {

    private var unit: () -> Unit = {}
    fun initUnit(newUnit: () -> Unit) {
        unit = newUnit
    }

    fun loginUser(authData: String) {
        viewModelScope.launch {
            val userModel = loginUserUseCase.execute(authData)
            if(userModel == null) {
                Log.i("LOGIN", "error")
            } else {
                setUsernameUseCase.execute(authData)
                unit()
                sendTokenUseCase.execute(token = getTokenUseCase.execute(), userModel.cookie)
            }
        }
    }

}