package com.android.achmadfuadagustian.presentation.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.android.achmadfuadagustian.data.interfaces.entities.search.RepositoryItemResult
import com.android.achmadfuadagustian.data.interfaces.interactor.search.SearchRepositoriesByQueryInteractor
import com.framework.base.BaseDataSourceFactory

class HomeDataFactory(
    private val searchRepositoriesByQueryInteractor: SearchRepositoriesByQueryInteractor, var query: String) : BaseDataSourceFactory<RepositoryItemResult>() {

    val homeMenuListLiveData = MutableLiveData<HomeListDataSource>()
    override fun createDataSource(): DataSource<Int, RepositoryItemResult> {
        val dataSource = HomeListDataSource(searchRepositoriesByQueryInteractor, query)
        homeMenuListLiveData.postValue(dataSource)
        return dataSource
    }
}