package com.example.prodapp.app

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.prodapp.di.appModule
import com.example.prodapp.di.dataModule
import com.example.prodapp.di.domainModule
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.binds
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()

//        koinApplication {
//            androidContext(this@App)
//            androidLogger(Level.DEBUG)
//            modules(listOf(appModule, domainModule, dataModule))
//        }
        startKoin {
//            androidContext(this@App)
            module {
                single { this@App } binds arrayOf(Context::class, Application::class)
            }
            androidLogger(Level.DEBUG)
            modules(listOf(appModule, domainModule, dataModule))
            androidContext(applicationContext)
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener {task ->
            if(!task.isSuccessful) {
                Log.i("TEST_PUSH", "false")
                return@addOnCompleteListener
            }


            val token = task.result
            Log.i("TEST_PUSH", "Token -> $token")
        }
        RemoteMessage.PRIORITY_HIGH

    }

}