package com.faraji.challenge.vira.app

import android.content.Context
import com.faraji.challenge.vira.data.remote.ApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun getApplicationModule(context: Context) =  module {
    single { context.applicationContext } bind Context::class


    single(named("okHttp")) {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    single {
       GsonBuilder()
           .create()
    }

    single(named("retrofit")) {
        Retrofit.Builder()
            .baseUrl("https://api.vira.challenge/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get(named("okHttp")))
            .build()
    }

    single {
    get<Retrofit>(named("retrofit")).create(ApiService::class.java)
}
}