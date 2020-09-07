package com.framework.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.framework.BR
import com.framework.owner.ViewDataBindingOwner
import com.framework.owner.ViewModelOwner
import com.framework.view.BindingViewHolder

abstract class BaseViewHolder<T>(val context: Context, view: View) : RecyclerView.ViewHolder(view) {

    init {
        if (this is ViewDataBindingOwner<*>) {
            setViewBinding(view)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, viewModel)
                binding.executePendingBindings();
            }
            if (this is BindingViewHolder<*>) {
                binding.setVariable(BR.view, this)
            }
        }
    }

    abstract fun bindData(data: T)
}
