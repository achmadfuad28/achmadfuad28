package com.framework.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Base64
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.framework.R

open class WebImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatImageView(context, attrs, defStyleAttr), UrlSourceImageView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    final override var loadingPlaceHolder: Int = R.color.webimageview_default_placeholder
    final override var errorPlaceHolder: Int = R.color.webimageview_default_error_placeholder
    private var imageRequestOptions: RequestOptions
    private var circleImageRequestOptions: RequestOptions
    private var roundedCornerImageRequestOptions: RequestOptions

    init {
        if (attrs != null) {
            val arrayOfAttrs = context.theme.obtainStyledAttributes(attrs,
                R.styleable.WebImageView, 0, 0)

            loadingPlaceHolder = arrayOfAttrs.getInt(
                R.styleable.WebImageView_loadingPlacehoder, loadingPlaceHolder)
            errorPlaceHolder = arrayOfAttrs.getInt(
                R.styleable.WebImageView_errorPlacehoder, errorPlaceHolder)
            arrayOfAttrs.recycle()
        }

        imageRequestOptions = RequestOptions()
            .placeholder(loadingPlaceHolder)
            .error(errorPlaceHolder)
            .diskCacheStrategy(DiskCacheStrategy.DATA)

        circleImageRequestOptions = RequestOptions()
            .placeholder(loadingPlaceHolder)
            .error(errorPlaceHolder)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.DATA)

        roundedCornerImageRequestOptions = RequestOptions()
            .placeholder(loadingPlaceHolder)
            .error(errorPlaceHolder)
            .centerCrop()
            .transform(RoundedCorners(16))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    }

    fun resetRequestOptions() {
        imageRequestOptions = RequestOptions()
            .placeholder(loadingPlaceHolder)
            .error(errorPlaceHolder)
            .diskCacheStrategy(DiskCacheStrategy.DATA)

        circleImageRequestOptions = RequestOptions()
            .placeholder(loadingPlaceHolder)
            .error(errorPlaceHolder)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.DATA)

        roundedCornerImageRequestOptions = RequestOptions()
            .placeholder(loadingPlaceHolder)
            .error(errorPlaceHolder)
            .centerCrop()
            .transform(RoundedCorners(16))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    }

    override fun setImageUrl(url: String) {
        Glide.with(context)
            .load(url)
            .apply(imageRequestOptions)
            .into(this)
    }

    fun setImageUrl(url: String, type: TransformType) {
        when (type) {
            TransformType.CIRCLE -> {
                Glide.with(context)
                    .load(url)
                    .apply(circleImageRequestOptions)
                    .into(this)
            }
            TransformType.ROUNDED_CORNER -> {
                Glide.with(context)
                    .load(url)
                    .apply(roundedCornerImageRequestOptions)
                    .into(this)
            }
            else -> setImageUrl(url)
        }
    }

    fun setImageBase64(base64: String) {
        Glide.with(context)
            .load(Base64.decode(base64, Base64.DEFAULT))
            .apply(imageRequestOptions)
            .into(this)
    }

    enum class TransformType {
        CIRCLE,
        ROUNDED_CORNER,
        NONE
    }
}