package com.example.prodapp.di

import com.example.prodapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> { MainViewModel(getListAllPublicationsUseCase = get(), getListChannelsUseCase = get()) }

}