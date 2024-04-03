package com.example.prodapp.di

import com.example.data.repository.ApiRepositoryImpl
import com.example.data.repository.SavedCookieRepositoryImpl
import com.example.domain.repository.SavedCookieRepository
import com.example.domain.usecase.GetCookieFromLocalSourceUseCase
import com.example.domain.usecase.GetListAllDraftPublicationUseCase
import com.example.domain.usecase.GetListAllPublicationsUseCase
import com.example.domain.usecase.GetListAllSchedulePublicationsUseCase
import com.example.domain.usecase.GetListAllSendPublicationsUseCase
import com.example.domain.usecase.GetListChannelsUseCase
import com.example.domain.usecase.GetPublicationByIdUseCase
import com.example.domain.usecase.GetTokenUseCase
import com.example.domain.usecase.GetUsernameUseCase
import com.example.domain.usecase.LoginUserUseCase
import com.example.domain.usecase.SendTokenUseCase
import com.example.domain.usecase.SetCookieToLocalSourceUseCase
import com.example.domain.usecase.SetTokenUseCase
import com.example.domain.usecase.SetUsernameUseCase
import com.example.domain.usecase.UpdatePostUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetListAllDraftPublicationUseCase> { GetListAllDraftPublicationUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<GetListAllSchedulePublicationsUseCase> { GetListAllSchedulePublicationsUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<GetListAllSendPublicationsUseCase> { GetListAllSendPublicationsUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<GetPublicationByIdUseCase> { GetPublicationByIdUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<GetListAllPublicationsUseCase> { GetListAllPublicationsUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<GetListChannelsUseCase> { GetListChannelsUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<GetCookieFromLocalSourceUseCase> { GetCookieFromLocalSourceUseCase(cookieRepository = get<SavedCookieRepositoryImpl>()) }

    factory<SetCookieToLocalSourceUseCase> { SetCookieToLocalSourceUseCase(cookieRepository = get<SavedCookieRepositoryImpl>()) }

    factory<LoginUserUseCase> { LoginUserUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<SetTokenUseCase> { SetTokenUseCase(savedCookieRepository = get<SavedCookieRepositoryImpl>()) }

    factory<GetTokenUseCase> { GetTokenUseCase(savedCookieRepository = get<SavedCookieRepositoryImpl>()) }

    factory<SendTokenUseCase> { SendTokenUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<UpdatePostUseCase> { UpdatePostUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<GetUsernameUseCase> { GetUsernameUseCase(savedCookieRepository = get<SavedCookieRepositoryImpl>()) }

    factory<SetUsernameUseCase> { SetUsernameUseCase(savedCookieRepository = get<SavedCookieRepositoryImpl>()) }

}