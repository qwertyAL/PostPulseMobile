package com.example.prodapp.di

import com.example.data.api.Api
import com.example.data.repository.ApiRepositoryImpl
import com.example.data.source.local.ApiLocalSource
import com.example.data.source.remote.ApiRemoteSource
import org.koin.dsl.module

val dataModule = module {

    single<Api> { Api() }

    single<ApiLocalSource> { ApiLocalSource() }

    single<ApiRemoteSource> { ApiRemoteSource(get()) }

    single<ApiRepositoryImpl> { ApiRepositoryImpl(apiLocalSource = get(), apiRemoteSource = get()) }

}