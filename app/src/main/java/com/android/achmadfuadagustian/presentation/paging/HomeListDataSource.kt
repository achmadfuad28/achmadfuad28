package com.android.achmadfuadagustian.presentation.paging

import com.android.achmadfuadagustian.data.interfaces.entities.search.ListFilter
import com.android.achmadfuadagustian.data.interfaces.entities.search.RepositoryItemResult
import com.android.achmadfuadagustian.data.interfaces.interactor.search.SearchRepositoriesByQueryInteractor
import com.framework.base.BasePageKeyedDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeListDataSource(private val searchRepositoriesByQueryInteractor: SearchRepositoriesByQueryInteractor, private val query: String)
    : BasePageKeyedDataSource<RepositoryItemResult>() {

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryItemResult>) {
        GlobalScope.launch {
            try {
                prepareLoadAfter()

                val filter = ListFilter()
                filter.pageNo = params.key
                val item = searchRepositoriesByQueryInteractor.executeAsync(SearchRepositoriesByQueryInteractor.Params(query, filter.pageNo))

                loadAfterSuccess()

                val adjacentPageKey = if (item.lastPage) null else filter.pageNo + 1
                callback.onResult(item.data!!, adjacentPageKey)
            } catch (e: Exception) {
                loadAfterError(params, callback, e)
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepositoryItemResult>) {
        GlobalScope.launch {
            try {
                prepareLoadInitial()

                val filter = ListFilter()
                filter.pageNo = 1
                val result = searchRepositoriesByQueryInteractor.executeAsync(SearchRepositoriesByQueryInteractor.Params(query, 1))

                if (result.data.isNullOrEmpty()) {
                    loadInitialEmpty()
                } else {
                    loadInitialSuccess()
                    val nextPageKey = if (result.lastPage) null else filter.pageNo + 1
                    callback.onResult(result.data!!, null, nextPageKey)
                }
            } catch (e: Exception) {
                loadInitialError(params, callback, e)
            }
        }
    }
}