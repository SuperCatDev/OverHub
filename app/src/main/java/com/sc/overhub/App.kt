package com.sc.overhub

import android.app.Application
import androidx.room.Room
import com.sc.overhub.data.AppDataBase
import com.sc.overhub.mapper.MapMapper
import com.sc.overhub.repository.MapsRepository
import com.sc.overhub.repository.MapsRepositoryImpl
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single<MapsRepository> { MapsRepositoryImpl(AppDataBase.getInstance(androidContext()).wikiMapsDao(), MapMapper()) }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}