package com.android.achmadfuadagustian.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.achmadfuadagustian.R
import com.android.achmadfuadagustian.data.base.utils.ViewUtils
import com.android.achmadfuadagustian.data.base.widget.LoadingView
import com.android.achmadfuadagustian.databinding.ActivityMainBinding
import com.android.achmadfuadagustian.presentation.adapter.ItemAdapter
import com.android.achmadfuadagustian.presentation.view.MainView
import com.android.achmadfuadagustian.presentation.viewmodel.MainViewModel
import com.framework.base.BaseActivity
import com.framework.common.NetworkState
import com.framework.common.extensions.showToast
import com.framework.owner.ViewDataBindingOwner
import com.framework.owner.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(),
    MainView,
    ViewModelOwner<MainViewModel>,
    ViewDataBindingOwner<ActivityMainBinding> {

    override lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModel()

    override lateinit var itemAdapter: ItemAdapter
    override var itemLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.let {
            setSupportActionBar(it)
            supportActionBar?.title = getString(R.string.app_title)
        }

        itemAdapter = ItemAdapter {
            viewModel.retry()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        binding.loadingView.showEmpty(
            getString(R.string.title_initial_state),
            getString(R.string.subtitle_initial_state),
            false
        )

        observeProgressStatus()
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_search, menu)

        val myActionMenuItem = menu?.findItem(R.id.action_search)
        val searchView = myActionMenuItem?.actionView as SearchView
        val searchClose = searchView.findViewById<ImageView>(R.id.search_close_btn)
        val searchText = searchView.findViewById<EditText>(R.id.search_src_text)
        searchText.hint = getString(R.string.hint_search)
        searchClose.setImageResource(R.drawable.ic_baseline_close)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(viewModel.ticketList == null) {
                    observeData(query)
                } else {
                    viewModel.searchByKeyword(query)
                }
                ViewUtils.hideKeyboard(this@MainActivity, binding.root)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchClose.setOnClickListener {
            searchText.setText(getString(R.string.default_empty))
            viewModel.bTextSearch.set(getString(R.string.default_empty))
        }

        return super.onCreateOptionsMenu(menu)
    }

    override var retryListener = object : LoadingView.OnRetryListener {
        override fun onRetry() {
            viewModel.refresh()
        }
    }

    private fun observeProgressStatus() {
        observeData(viewModel.getInitialState()) { initialState ->
            initialState?.let {
                when (initialState) {
                    NetworkState.LOADING -> {
                        binding.loadingView.showLoading()
                        viewModel.showLoading()
                    }
                    NetworkState.LOADED -> {
                        binding.swipeRefresh.isRefreshing = false
                        viewModel.hideLoading()

                    }
                    NetworkState.EMPTY -> {
                        viewModel.showLoading()
                        binding.loadingView.showEmpty(
                            getString(R.string.title_empty_state),
                            getString(R.string.subtitle_empty_state),
                            false
                        )
                    }
                    else -> {
                        binding.swipeRefresh.isRefreshing = false
                        viewModel.showLoading()
                        initialState.exception?.message?.let {
                            binding.loadingView.showError("", it)
                        }
                    }
                }
            }

            observeData(viewModel.getNetworkState()) { networkState ->
                networkState?.let { state ->
                    itemAdapter.setNetworkState(state)
                    when (state) {
                        NetworkState.LOADING -> {
                            viewModel.showLoadingPlaceHolder()
                        }
                        NetworkState.LOADED -> {
                            binding.swipeRefresh.isRefreshing = false
                            viewModel.hideLoadingPlaceHolder()
                        }
                        else -> {
                            viewModel.hideLoadingPlaceHolder()
                            if (state.exception != null) {
                                binding.rvRepositoryList.smoothScrollToPosition(itemAdapter.itemCount)
                            }

                            state.exception?.message?.let {
                                if (it.contains("limit"))
                                    showToast(getString(R.string.message_search_limit))
                                else
                                    showToast(it)
                            }

                        }
                    }
                }
            }
        }
    }

    private fun observeData(query: String) {
        observeData(viewModel.getHomeMenuList(query)) { members ->
            members?.let {
                itemAdapter.submitList(members)
            }
        }
    }

}