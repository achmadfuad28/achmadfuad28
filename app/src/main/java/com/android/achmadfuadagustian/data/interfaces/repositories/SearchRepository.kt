package com.android.achmadfuadagustian.data.interfaces.repositories

import com.android.achmadfuadagustian.data.interfaces.entities.search.SearchRepositoriesResult

interface SearchRepository {

    /**
     * search in Repository
     *
     * @query[q] The query used to retrieve repositories data
     * @return [SearchRepositoriesResult]
     * @throws [Exception]
     */
    @Throws(Exception::class)
    fun searchRepositories(query: String?, page: Int): SearchRepositoriesResult

}