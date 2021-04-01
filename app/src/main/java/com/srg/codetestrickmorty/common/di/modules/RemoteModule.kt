package com.srg.codetestrickmorty.common.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.srg.codetestrickmorty.common.adapters.LocalDateTimeAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Module
object RemoteModule {

    private const val READ_TIMEOUT_SECONDS = 40L
    private const val WRITE_TIMEOUT_SECONDS = 40L

    @Provides
    fun provideRetrofitService(context: Context): Retrofit = Retrofit.Builder()
        .baseUrl("")
        .client(provideOkHttpClient(context))
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().registerTypeAdapter(
                    LocalDateTime::class.java,
                    LocalDateTimeAdapter()
                ).create()
            )
        )
        .build()

    private fun provideOkHttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .build()

}