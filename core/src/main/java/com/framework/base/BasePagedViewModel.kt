package com.framework.base

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.framework.common.NetworkState

abstract class BasePagedViewModel : BaseViewModel() {
    protected var mNetworkState: LiveData<NetworkState>? = null
    protected var mInitialState: LiveData<NetworkState>? = null

    protected fun observeState() {
        mInitialState?.let { state ->
            if (!state.hasActiveObservers()) {
                state.observeForever {
                    initialState.value = it
                }
            }
        }

        mNetworkState?.let { state ->
            if (!state.hasActiveObservers()) {
                state.observeForever {
                    networkState.value = it
                }
            }
        }
    }

    protected fun pagedListConfig(pageSize: Int): PagedList.Config {
        return PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(pageSize)
            .setPageSize(pageSize)
            .build()
    }

    abstract fun refresh()
    abstract fun retry()
}