package com.android.achmadfuadagustian.presentation.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.android.achmadfuadagustian.data.base.widget.LoadingView
import com.android.achmadfuadagustian.presentation.adapter.ItemAdapter
import com.framework.view.LifecycleView

interface MainView : LifecycleView {
    var itemAdapter: ItemAdapter
    var itemLayoutManager: LinearLayoutManager
    var retryListener: LoadingView.OnRetryListener
}