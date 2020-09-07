package com.framework.widget

interface UrlSourceImageView {

    val loadingPlaceHolder: Int
    val errorPlaceHolder: Int

    fun setImageUrl(url: String)
}
