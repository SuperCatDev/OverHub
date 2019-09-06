package com.sc.overhub.di

import com.sc.overhub.data.db.AppDataBase
import com.sc.overhub.data.repository.DbRepositoryFactory
import com.sc.overhub.data.repository.ProfileRepository
import com.sc.overhub.data.repository.ProfileRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { AppDataBase.getInstance(androidApplication()) }
    single { DbRepositoryFactory.mapsReposImpl }
    single { DbRepositoryFactory.heroesReposImp }
    single { DbRepositoryFactory.arcadeReposImp }
    single { ProfileRepositoryImpl(androidApplication()) } bind ProfileRepository::class
}