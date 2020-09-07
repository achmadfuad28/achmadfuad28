package com.android.achmadfuadagustian.di.components

import org.koin.core.module.Module
import com.android.achmadfuadagustian.di.modules.applicationApiModule
import com.android.achmadfuadagustian.di.modules.searchRepositoryModule

val baseApiComponent: List<Module> = listOf(
        applicationApiModule,
        searchRepositoryModule)