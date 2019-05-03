package com.sc.overhub

import android.app.Application
import com.sc.overhub.repository.ProfileRepository
import com.sc.overhub.repository.RepositoryFactory
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { RepositoryFactory.mapsReposImpl }
    single { RepositoryFactory.heroesReposImp }
    single { RepositoryFactory.arcadeReposImp }
    single { ProfileRepository(androidContext()) }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}