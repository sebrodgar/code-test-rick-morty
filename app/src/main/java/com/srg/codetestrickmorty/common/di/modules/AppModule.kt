package com.srg.codetestrickmorty.common.di.modules

import android.content.Context
import com.srg.codetestrickmorty.common.CodeTestRickMortyApplication
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object AppModule {

    @Provides
    fun provideApplicationContext(application: CodeTestRickMortyApplication): Context =
        application.applicationContext

    @Provides
    fun provideViewModelDispatcher(): CoroutineDispatcher = Dispatchers.Main
}