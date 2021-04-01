package com.srg.codetestrickmorty.common.di.modules

import com.srg.codetestrickmorty.data.features.characters.sources.CharactersDataSource
import com.srg.codetestrickmorty.data.features.characters.sources.CharactersDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindCharactersDataSource(dataSource: CharactersDataSourceImpl): CharactersDataSource
}