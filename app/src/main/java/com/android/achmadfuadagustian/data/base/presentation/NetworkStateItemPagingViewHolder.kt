package com.android.achmadfuadagustian.data.base.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.achmadfuadagustian.R
import com.android.achmadfuadagustian.databinding.NetworkStateItemBinding
import com.framework.base.BaseViewHolder
import com.framework.common.NetworkState
import com.framework.owner.ViewDataBindingOwner

class NetworkStateItemPagingViewHolder(context: Context, view: View,
                                       private val retryCallback: () -> Unit)
    : BaseViewHolder<NetworkState>(context, view),
    ViewDataBindingOwner<NetworkStateItemBinding> {
    override lateinit var binding: NetworkStateItemBinding
    init {
        binding.retryButton.setOnClickListener {
            retryCallback()
        }
    }
    override fun bindData(data: NetworkState) {
        binding.loadingAnimationView.visibility = toVisibility(data.status == NetworkState.Status.RUNNING)
        binding.retryButton.visibility = toVisibility(data.status == NetworkState.Status.FAILED)
        binding.errorMsg.visibility = toVisibility(data.exception != null)
        binding.errorMsg.text = data.exception?.message
    }
    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateItemPagingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemPagingViewHolder(parent.context, view, retryCallback)
        }
        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}