package com.android.achmadfuadagustian.di.modules

import com.android.achmadfuadagustian.presentation.viewmodel.MainViewModel
import com.android.achmadfuadagustian.presentation.viewmodel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        SplashScreenViewModel()
    }
    viewModel {
        MainViewModel(get())
    }
}