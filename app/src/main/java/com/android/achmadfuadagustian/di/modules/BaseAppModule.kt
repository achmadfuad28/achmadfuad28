package com.android.achmadfuadagustian.di.modules

import android.app.Application
import android.content.SharedPreferences
import com.framework.di.module.AppModule
import org.koin.dsl.module

class BaseAppModule(app: Application, language: String) : AppModule(app, language)

val baseAppModule = module {
    factory { (app: Application, language: String) -> BaseAppModule(app, language) }
    single<SharedPreferences> { get() }
}