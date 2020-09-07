package com.android.achmadfuadagustian

import android.annotation.SuppressLint
import android.provider.Settings
import com.android.achmadfuadagustian.data.base.constant.KoinPropertyConstants
import com.android.achmadfuadagustian.di.components.baseApiComponent
import com.android.achmadfuadagustian.di.components.baseAppComponent
import com.framework.base.BaseMultidexApplication
import com.framework.webapi.WebApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class BaseApplication : BaseMultidexApplication() {

    private val deviceId: String
        @SuppressLint("HardwareIds")
        get() = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

    @SuppressLint("MissingSuperCall")
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun createApi(): WebApi {
        return WebApi.Builder("https://api.github.com/", "1.0.0")
            .setConnectTimeout(30)
            .setReadTimeout(30)
            .setLoggingEnabled(BuildConfig.DEBUG)
            .setHttpInspector(this, BuildConfig.DEBUG)
            .build()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            properties(mapOf(Pair(KoinPropertyConstants.API_PROPERTY, createApi()),
                Pair(KoinPropertyConstants.DEVICE_ID_PROPERTY, deviceId),
                Pair(KoinPropertyConstants.APP_VERSION_PROPERTY, BuildConfig.VERSION_NAME),
                Pair(KoinPropertyConstants.APPLICATION_CONTEXT_PROPERTY, applicationContext)))
            modules(baseApiComponent)
            modules(baseAppComponent)
        }
    }
}
