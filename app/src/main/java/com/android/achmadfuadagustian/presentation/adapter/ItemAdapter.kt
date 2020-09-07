package com.android.achmadfuadagustian.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.android.achmadfuadagustian.R
import com.android.achmadfuadagustian.data.base.presentation.NetworkStateItemPagingViewHolder
import com.android.achmadfuadagustian.data.interfaces.entities.search.RepositoryItemResult
import com.android.achmadfuadagustian.databinding.ItemListBinding
import com.android.achmadfuadagustian.presentation.view.ItemView
import com.android.achmadfuadagustian.presentation.viewmodel.ItemViewModel
import com.framework.base.BasePagedListAdapter
import com.framework.base.BaseViewHolder
import com.framework.owner.ViewDataBindingOwner
import com.framework.widget.WebImageView

class ItemAdapter(retryCallback: () -> Unit) : BasePagedListAdapter<RepositoryItemResult>(ITEM_COMPARATOR, retryCallback) {

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<RepositoryItemResult>() {
            override fun areItemsTheSame(oldItem: RepositoryItemResult, newItem: RepositoryItemResult): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: RepositoryItemResult, newItem: RepositoryItemResult): Boolean =
                oldItem == newItem
        }
    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_list -> (holder as ViewHolder).bindData(getItem(position)!!)
            R.layout.network_state_item -> (holder as NetworkStateItemPagingViewHolder).bindData(getNetworkState()!!)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.item_list
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            R.layout.item_list -> ViewHolder.create(parent)
            R.layout.network_state_item -> NetworkStateItemPagingViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    class ViewHolder(
        context: Context,
        view: View
    ) :
        ItemView,
        BaseViewHolder<RepositoryItemResult>(context, view),
        ViewDataBindingOwner<ItemListBinding> {

        override lateinit var binding: ItemListBinding
        private lateinit var data: RepositoryItemResult
        private var viewModel: ItemViewModel? = null

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list, parent, false)
                return ViewHolder(parent.context, view)
            }
        }

        init {
            binding.vm = ItemViewModel()
            binding.view = this
            viewModel = binding.vm
        }

        override fun bindData(data: RepositoryItemResult) {
            this.data = data
            viewModel?.resetData()
            viewModel?.setData(data)
            data.ownerAvatar?.let {
                binding.imgOwnerAvatar.setImageUrl(it, WebImageView.TransformType.ROUNDED_CORNER)
            }

        }


    }
}