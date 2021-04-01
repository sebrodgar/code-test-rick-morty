package com.srg.codetestrickmorty.common.di.modules

import android.content.Context
import com.srg.codetestrickmorty.common.db.RickMortyDatabase
import com.srg.codetestrickmorty.data.features.characters.RickMortyApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object DataProvidersModule {

    @Provides
    fun provideRickMortyApiService(retrofit: Retrofit): RickMortyApiService =
        retrofit.create(RickMortyApiService::class.java)

    @Provides
    fun provideRickMortyDatabase(context: Context): RickMortyDatabase =
        RickMortyDatabase.getInstance(context)
}