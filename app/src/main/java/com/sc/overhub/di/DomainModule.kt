package com.sc.overhub.di

import com.sc.overhub.domain.interactor.*
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    single { LeaveFromAccountInteractorImpl(get()) } bind LeaveFromAccountInteractor::class
    single { LaunchInteractorImpl(get(), get()) } bind LaunchInteractor::class
    single { ArcadeInteractorImpl(get()) } bind ArcadeInteractor::class
}
