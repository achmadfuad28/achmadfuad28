package com.android.achmadfuadagustian.data.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import androidx.annotation.DrawableRes
import com.android.achmadfuadagustian.R

class LoadingView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : RelativeLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        injectViews()
    }

    enum class State {
        LOADING, ERROR, EMPTY
    }

    private var state: State = State.ERROR
    private var progressBar: ProgressBar? = null
    private var imageView: ImageView? = null
    private var progressTitle: TextView? = null
    private var progressMessage: TextView? = null
    private var retryButton: Button? = null
    private var listener: OnRetryListener? = null

    private fun injectViews() {
        inflate(context, R.layout.loading_view, this)
        progressBar = findViewById(R.id.progressBar)
        progressTitle = findViewById(R.id.progressTitle)
        progressMessage = findViewById(R.id.progressMessage)
        retryButton = findViewById(R.id.retryButton)
        retryButton?.setOnClickListener { view -> onClickRetry(view) }
        setDefaultRetrybutton()
        showLoading()
    }

    fun setOnRetryListener(listener: OnRetryListener) {
        this.listener = listener
    }

    private fun onClickRetry(view: View) {
        listener?.let {
            showLoading()
            it.onRetry()
        }
    }

    fun showLoading() {
        state = State.LOADING
        progressTitle?.text = null
        progressMessage?.text = context.getString(R.string.label_loading)
        showImage(0)
        showButton()
        showProgress()
    }

    fun showProgress() {
        progressBar?.visibility = when (state) {
            State.LOADING -> View.VISIBLE
            else -> View.GONE
        }
        progressTitle?.visibility = when (state) {
            State.LOADING -> View.GONE
            else -> View.VISIBLE
        }
    }

    private fun showButton(show: Boolean) {
        retryButton?.visibility = when (show) {
            false -> View.GONE
            true -> View.VISIBLE
        }
    }

    private fun showButton() {
        retryButton?.visibility = when (state) {
            State.LOADING -> View.GONE
            else -> View.VISIBLE
        }
    }

    private fun showImage(@DrawableRes icon: Int) {
        imageView?.visibility = when (icon) {
            0 -> View.GONE
            else -> View.VISIBLE
        }
        imageView?.setImageResource(icon)
    }

    fun showEmpty(title: String, message: String, showButton: Boolean) {
        showEmpty(title, message, null, 0, showButton)
    }

    fun showEmpty(title: String, message: String, buttonText: String?, @DrawableRes icon: Int, showButton: Boolean) {
        state = State.EMPTY
        progressTitle?.text = title
        progressMessage?.text = message
        if (buttonText.isNullOrEmpty())
            setDefaultRetrybutton()
        else
            retryButton?.text = buttonText
        retryButton?.visibility = when (showButton) {
            true -> View.VISIBLE
            false -> View.GONE
        }
        showImage(icon)
        showProgress()
    }

    fun showError(title: String, message: String) {
        state = State.ERROR
        progressTitle?.text = title
        progressMessage?.text = message
        showButton()
        showProgress()
    }

    private fun setDefaultRetrybutton() {
        retryButton?.text = context.getString(R.string.label_try_again)
    }

    interface OnRetryListener {
        fun onRetry()
    }
}