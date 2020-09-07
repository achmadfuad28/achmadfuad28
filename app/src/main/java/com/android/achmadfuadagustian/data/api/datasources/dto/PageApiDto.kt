package com.android.achmadfuadagustian.data.api.datasources.dto

import com.google.gson.annotations.SerializedName

data class PageApiDto<T>(

    @SerializedName("incomplete_results")
    val incompleteResult: Boolean = false,
    @SerializedName("total_count")
    val totalCount: Int? = null,
    @SerializedName("items")
    val items: List<T>?
)