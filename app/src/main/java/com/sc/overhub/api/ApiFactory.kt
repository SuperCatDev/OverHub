package com.sc.overhub.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory{

    private val interception: HttpLoggingInterceptor = HttpLoggingInterceptor()

    private val arcadeClient = OkHttpClient().newBuilder()
        .addInterceptor(interception)
        .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(arcadeClient)
        .baseUrl("https://overwatcharcade.today/api")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val arcadeApi: ArcadeApi = retrofit().create(ArcadeApi::class.java)




}