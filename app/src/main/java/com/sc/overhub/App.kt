package com.sc.overhub

import android.app.Application
import com.sc.overhub.data.repository.DbRepositoryFactory
import com.sc.overhub.data.repository.ProfileRepository
import com.sc.overhub.data.repository.ProfileRepositoryImpl
import com.sc.overhub.domain.usecase.LeaveFromAccountUseCase
import com.sc.overhub.domain.usecase.LeaveFromAccountUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { DbRepositoryFactory.mapsReposImpl }
    single { DbRepositoryFactory.heroesReposImp }
    single { DbRepositoryFactory.arcadeReposImp }
    single { ProfileRepositoryImpl(androidContext()) } bind ProfileRepository::class
    single { LeaveFromAccountUseCaseImpl() } bind LeaveFromAccountUseCase::class
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}