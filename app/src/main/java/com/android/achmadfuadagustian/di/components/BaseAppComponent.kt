package com.android.achmadfuadagustian.di.components

import com.android.achmadfuadagustian.di.modules.baseAppModule
import com.android.achmadfuadagustian.di.modules.mainViewModelModule
import org.koin.core.module.Module

val baseAppComponent: List<Module> = listOf(
        baseAppModule,
        mainViewModelModule
)