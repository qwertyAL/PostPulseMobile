package com.example.prodapp

import android.util.Log
import android.webkit.JavascriptInterface
import com.example.prodapp.viewmodel.LoginViewModel

class JavascriptInterface(
    private val loginViewModel: LoginViewModel,
    private val function: () -> Unit
) {

    @JavascriptInterface
    fun callbackFunction(authData: String) {
        Log.i("TEST_JAVA_INTERFACE", authData.toString())

        loginViewModel.loginUser(authData) { function }
    }

}