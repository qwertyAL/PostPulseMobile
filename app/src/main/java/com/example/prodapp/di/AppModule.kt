package com.example.prodapp.di

import com.example.domain.usecase.UpdatePostUseCase
import com.example.prodapp.viewmodel.LoginViewModel
import com.example.prodapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> { MainViewModel(getListAllPublicationsUseCase = get(), getListChannelsUseCase = get(), getCookieFromLocalSourceUseCase = get(), getListAllDraftPublicationUseCase = get(), getListAllSchedulePublicationsUseCase = get(), getListAllSendPublicationsUseCase = get(), updatePostUseCase = get(), getUsernameUseCase = get()) }

    viewModel<LoginViewModel> { LoginViewModel(loginUserUseCase = get(), sendTokenUseCase = get(), getTokenUseCase = get(), setUsernameUseCase = get()) }

}