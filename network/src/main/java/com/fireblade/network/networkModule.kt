package com.fireblade.network

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single {
        Json {
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
        }
    }

    single {
        val client = OkHttpClient.Builder().build()
        Retrofit.Builder()
            .baseUrl("http://163.172.147.216:8080")
            .client(client)
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single {
        get<Retrofit>().create(IFileNetworkService::class.java)
    }
}