package com.example.prodapp.notifications

import com.example.domain.usecase.SetTokenUseCase
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseInstanceIDService(
    private val setTokenUseCase: SetTokenUseCase
): FirebaseMessagingService() {

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)

        setTokenUseCase.execute(newToken)

    }
}