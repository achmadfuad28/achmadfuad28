package com.framework.base

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.framework.common.NetworkState

abstract class BasePagedListAdapter<T>(callback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, BaseViewHolder<*>>(callback) {
    lateinit var retryCallback: () -> Unit

    private var networkState: NetworkState? = null

    constructor(callback: DiffUtil.ItemCallback<T>, retryCallback: () -> Unit) : this(callback) {
        this.retryCallback = retryCallback
    }

    protected fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    open fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    protected fun getNetworkState(): NetworkState? {
        return networkState
    }
}