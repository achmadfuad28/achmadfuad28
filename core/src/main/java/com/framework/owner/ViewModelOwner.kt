package com.framework.owner

import com.framework.base.BaseViewModel

interface ViewModelOwner<T : BaseViewModel> {
    val viewModel: T
}