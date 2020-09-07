package com.framework.view

import android.view.View

interface BindingViewHolder<in T> {

    fun onItemClick(view: View, item: T)
}
