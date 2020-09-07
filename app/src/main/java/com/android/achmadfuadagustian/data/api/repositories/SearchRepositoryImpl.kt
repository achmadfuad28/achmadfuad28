package com.android.achmadfuadagustian.data.api.repositories

import com.android.achmadfuadagustian.data.api.datasources.BaseApiException
import com.android.achmadfuadagustian.data.api.datasources.services.SearchApiService
import com.android.achmadfuadagustian.data.api.mappers.SearchRepositoriesMapper
import com.android.achmadfuadagustian.data.interfaces.entities.search.SearchRepositoriesResult
import com.android.achmadfuadagustian.data.interfaces.repositories.SearchRepository
import retrofit2.HttpException

class SearchRepositoryImpl(private val service: SearchApiService,
                           private val searchRepositoriesMapper: SearchRepositoriesMapper
) : SearchRepository {

    override fun searchRepositories(query: String?, page: Int): SearchRepositoriesResult {
        val response = service.searchRepositories(query, page).execute()
        return if (response.isSuccessful) {
            searchRepositoriesMapper.transform(response.body()!!)
        } else {
            throw BaseApiException(response.message(), HttpException(response))
        }
    }
}