package com.android.achmadfuadagustian.data.api.datasources

import com.framework.webapi.WebApi
import okhttp3.Interceptor
import okhttp3.Response

class BaseNetworkInterceptor(val api: WebApi) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        return chain.proceed(request.build())
    }

}