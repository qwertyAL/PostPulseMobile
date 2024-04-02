package com.example.prodapp.di

import com.example.data.repository.ApiRepositoryImpl
import com.example.data.repository.SavedCookieRepositoryImpl
import com.example.domain.usecase.GetCookieFromLocalSourceUseCase
import com.example.domain.usecase.GetListAllDraftPublicationUseCase
import com.example.domain.usecase.GetListAllPublicationsUseCase
import com.example.domain.usecase.GetListAllSchedulePublicationsUseCase
import com.example.domain.usecase.GetListAllSendPublicationsUseCase
import com.example.domain.usecase.GetListChannelsUseCase
import com.example.domain.usecase.GetPublicationByIdUseCase
import com.example.domain.usecase.LoginUserUseCase
import com.example.domain.usecase.SetCookieToLocalSourceUseCase
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

}