package com.android.achmadfuadagustian.di.modules

import com.android.achmadfuadagustian.data.api.datasources.services.SearchApiService
import com.android.achmadfuadagustian.data.api.mappers.SearchRepositoriesMapper
import com.android.achmadfuadagustian.data.api.repositories.SearchRepositoryImpl
import com.android.achmadfuadagustian.data.interfaces.interactor.search.SearchRepositoriesByQueryInteractor
import com.android.achmadfuadagustian.data.interfaces.repositories.SearchRepository
import com.framework.webapi.ServiceFactory
import org.koin.dsl.module

val searchRepositoryModule = module {
    single {
        get<ServiceFactory>().createService(SearchApiService::class.java)
    }
    single { SearchRepositoriesMapper() }
    single<SearchRepository> {
        SearchRepositoryImpl(
            service = get(),
            searchRepositoriesMapper = get())
    }

    factory { SearchRepositoriesByQueryInteractor(get()) }
}