package com.sc.overhub

import android.app.Application
import com.sc.overhub.repository.RepositoryFactory
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

val appModule = module {
    single { RepositoryFactory.mapsReposImpl }
    single { RepositoryFactory.mapsReposImpl }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}