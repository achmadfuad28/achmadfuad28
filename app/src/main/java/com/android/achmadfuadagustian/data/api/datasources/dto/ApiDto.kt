package com.android.achmadfuadagustian.data.api.datasources.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiDto<T>(

    @SerializedName("code")
    val statusCode: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: T?
)