package com.example.prodapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel (
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    fun loginUser(authData: String, function: () -> Unit) {
        viewModelScope.launch {
            val userModel = loginUserUseCase.execute(authData)
            if(userModel == null) {
                Log.i("LOGIN", "error")
            } else {
                function
            }
        }
    }

}