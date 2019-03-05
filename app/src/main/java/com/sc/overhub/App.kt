package com.sc.overhub

import android.app.Application
import com.sc.overhub.repository.MapsRepository
import com.sc.overhub.repository.MapsRepositoryImpl
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

val appModule = module {
    single<MapsRepository> { MapsRepositoryImpl() }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}