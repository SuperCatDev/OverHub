package com.sc.overhub.di

import com.sc.overhub.domain.interactor.LaunchInteractor
import com.sc.overhub.domain.interactor.LaunchInteractorImpl
import com.sc.overhub.domain.interactor.LeaveFromAccountInteractor
import com.sc.overhub.domain.interactor.LeaveFromAccountInteractorImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    single { LeaveFromAccountInteractorImpl(get()) } bind LeaveFromAccountInteractor::class
    single { LaunchInteractorImpl(get(), get()) } bind LaunchInteractor::class
}
