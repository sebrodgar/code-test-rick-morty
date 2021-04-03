package com.srg.codetestrickmorty.common.di.modules

import com.srg.codetestrickmorty.data.features.characters.sources.CharactersDataSource
import com.srg.codetestrickmorty.data.features.characters.sources.CharactersDataSourceImpl
import com.srg.codetestrickmorty.data.features.locations.sources.LocationsDataSource
import com.srg.codetestrickmorty.data.features.locations.sources.LocationsDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindCharactersDataSource(dataSource: CharactersDataSourceImpl): CharactersDataSource

    @Binds
    abstract fun bindLocationsDataSource(dataSource: LocationsDataSourceImpl): LocationsDataSource
}