package com.srg.codetestrickmorty.common.di.modules

import androidx.paging.ExperimentalPagingApi
import com.srg.codetestrickmorty.data.features.characters.CharactersRepositoryImpl
import com.srg.codetestrickmorty.data.features.locations.LocationsRepositoryImpl
import com.srg.codetestrickmorty.domain.features.characters.CharactersRepository
import com.srg.codetestrickmorty.domain.features.locations.LocationsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @ExperimentalPagingApi
    @Binds
    abstract fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @ExperimentalPagingApi
    @Binds
    abstract fun bindLocationsRepository(repository: LocationsRepositoryImpl): LocationsRepository
}