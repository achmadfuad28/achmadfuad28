package com.android.achmadfuadagustian.data.interfaces.interactor.search

import com.android.achmadfuadagustian.data.interfaces.entities.search.SearchRepositoriesResult
import com.android.achmadfuadagustian.data.interfaces.interactor.experimental.Interactor
import com.android.achmadfuadagustian.data.interfaces.repositories.SearchRepository

class SearchRepositoriesByQueryInteractor(val repository: SearchRepository) :
        Interactor<SearchRepositoriesByQueryInteractor.Params, SearchRepositoriesResult>() {

    override fun execute(params: Params?): SearchRepositoriesResult {
        val query = params?.query
                ?: throw IllegalArgumentException("missing params: {${Params::query}}")
        val page = params?.page

        return repository.searchRepositories(query, page)
    }

    data class Params(val query: String, val page: Int)
}