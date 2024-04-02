package com.example.prodapp.di

import com.example.data.api.Api
import com.example.data.repository.ApiRepositoryImpl
import com.example.data.repository.SavedCookieRepositoryImpl
import com.example.data.source.local.ApiLocalSource
import com.example.data.source.local.CookieLocalStore
import com.example.data.source.remote.ApiRemoteSource
import org.koin.dsl.module

val dataModule = module {

    single<Api> { Api() }

    single<ApiLocalSource> { ApiLocalSource() }

    single<ApiRemoteSource> { ApiRemoteSource(get()) }

    single<ApiRepositoryImpl> { ApiRepositoryImpl(apiLocalSource = get(), apiRemoteSource = get(), savedCookieRepositoryImpl = get<SavedCookieRepositoryImpl>()) }

    single<CookieLocalStore> { CookieLocalStore(context = get()) }

    single<SavedCookieRepositoryImpl> { SavedCookieRepositoryImpl(cookieLocalStore = get()) }

}