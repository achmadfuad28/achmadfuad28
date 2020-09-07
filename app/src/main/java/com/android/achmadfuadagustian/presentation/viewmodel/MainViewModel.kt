package com.android.achmadfuadagustian.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.achmadfuadagustian.data.interfaces.entities.search.RepositoryItemResult
import com.android.achmadfuadagustian.data.interfaces.interactor.search.SearchRepositoriesByQueryInteractor
import com.android.achmadfuadagustian.presentation.paging.HomeDataFactory
import com.android.achmadfuadagustian.presentation.paging.HomeListDataSource
import com.framework.base.BasePagedViewModel

class MainViewModel(private val searchRepositoriesByQueryInteractor: SearchRepositoriesByQueryInteractor) : BasePagedViewModel() {
    var bTextSearch = ObservableField<String>()
    val showPlaceHolderLoadingView = ObservableField<Boolean>(false)

    private var sourceFactory: HomeDataFactory? = null

    var ticketList: LiveData<PagedList<RepositoryItemResult>>? = null

    fun getHomeMenuList(query: String): LiveData<PagedList<RepositoryItemResult>> {
        if (ticketList == null) {
            getHomeMenuListByType(query)
        }
        return ticketList as LiveData<PagedList<RepositoryItemResult>>
    }

    override fun refresh() {
        sourceFactory?.homeMenuListLiveData?.value?.invalidate()
    }

    override fun retry() {
        sourceFactory?.homeMenuListLiveData?.value?.retryAllFailed()
    }

    private fun getHomeMenuListByType(query: String) {
        val pagedListConfig = pagedListConfig(10)
        sourceFactory = homeMenuDataSourceFactory(query)
        ticketList = LivePagedListBuilder(sourceFactory!!, pagedListConfig)
            .build()
        mNetworkState = Transformations.switchMap(
            sourceFactory!!.homeMenuListLiveData,
            HomeListDataSource::network
        )
        mInitialState = Transformations.switchMap(
            sourceFactory!!.homeMenuListLiveData,
            HomeListDataSource::initial
        )
        observeState()
    }

    fun searchByKeyword(keyword: String) {
        sourceFactory?.apply {
            query = keyword
            create()
            homeMenuListLiveData.value?.invalidate()
        }
    }

    val showLoadingView = ObservableField<Boolean>(true)

    private fun homeMenuDataSourceFactory(query:String): HomeDataFactory {
        return HomeDataFactory(searchRepositoriesByQueryInteractor, query)
    }
    fun showLoading() {
        showLoadingView.set(true)
    }
    fun hideLoading() {
        showLoadingView.set(false)
    }
    fun showLoadingPlaceHolder() {
        showPlaceHolderLoadingView.set(true)
    }
    fun hideLoadingPlaceHolder() {
        showPlaceHolderLoadingView.set(false)
    }
}
