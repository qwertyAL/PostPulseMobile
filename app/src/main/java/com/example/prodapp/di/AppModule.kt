package com.example.prodapp.di

import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.prodapp.viewmodel.LoginViewModel
import org.koin.dsl.module

val appModule = module {
    viewModelFactory { LoginViewModel() }
}