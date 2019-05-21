package com.sc.overhub

import android.app.Application
import com.sc.overhub.data.repository.ProfileRepository
import com.sc.overhub.data.repository.ProfileRepositoryImpl
import com.sc.overhub.data.repository.DbRepositoryFactory
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { DbRepositoryFactory.mapsReposImpl }
    single { DbRepositoryFactory.heroesReposImp }
    single { DbRepositoryFactory.arcadeReposImp }
    single { ProfileRepositoryImpl(androidContext()) as ProfileRepository }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}