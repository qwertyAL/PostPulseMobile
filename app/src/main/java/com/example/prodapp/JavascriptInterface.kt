package com.example.prodapp

import android.util.Log
import android.webkit.JavascriptInterface
import androidx.navigation.NavController
import com.example.prodapp.viewmodel.LoginViewModel

class JavascriptInterface(
    private val loginViewModel: LoginViewModel
) {

    @JavascriptInterface
    fun callbackFunction(authData: String) {
        Log.i("TEST_JAVA_INTERFACE", authData.toString())

        loginViewModel.loginUser(authData)
    }

}