package com.android.achmadfuadagustian.di.modules

import okhttp3.Interceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.android.achmadfuadagustian.data.api.datasources.BaseNetworkInterceptor
import com.android.achmadfuadagustian.data.base.constant.KoinPropertyConstants.API_PROPERTY
import com.android.achmadfuadagustian.data.base.constant.KoinPropertyConstants.APP_VERSION_PROPERTY
import com.android.achmadfuadagustian.data.base.constant.KoinPropertyConstants.DEVICE_ID_PROPERTY
import com.framework.di.module.ApiModule
import com.framework.webapi.ServiceFactory
import com.framework.webapi.WebApi
import com.framework.webapi.impl.RetrofitServiceFactory
import retrofit2.converter.gson.GsonConverterFactory

class BaseApiModule(api: WebApi, val deviceId: String, val appVersion: String) : ApiModule(api)

val applicationApiModule = module {
    single {
        BaseApiModule(getProperty(API_PROPERTY), getProperty(DEVICE_ID_PROPERTY), getProperty(APP_VERSION_PROPERTY))
    }
    single<Interceptor> {
        BaseNetworkInterceptor(
                api = get<BaseApiModule>().api
        )
    }
    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    single<Converter.Factory> { GsonConverterFactory.create() }
    single<ServiceFactory> {
        RetrofitServiceFactory(
                api = get<BaseApiModule>().api,
                interceptor = get(),
                adapterFactory = get(),
                converterFactory = get()
        )
    }
}