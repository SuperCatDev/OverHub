package com.sc.overhub

import android.app.Application
import com.sc.overhub.data.AppDataBase
import com.sc.overhub.mapper.MapMapper
import com.sc.overhub.repository.MapsRepository
import com.sc.overhub.repository.MapsRepositoryImpl
import com.sc.overhub.repository.RepositoryFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { RepositoryFactory.reposImpl }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}