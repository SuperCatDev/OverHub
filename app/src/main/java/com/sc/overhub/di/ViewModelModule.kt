package com.sc.overhub.di

import com.sc.overhub.presentation.viewmodel.LaunchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LaunchViewModel(get()) }
}