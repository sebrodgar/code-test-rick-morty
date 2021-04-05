package com.srg.codetestrickmorty.data.features.locations.sources

import com.srg.codetestrickmorty.common.errors.toDomain
import com.srg.codetestrickmorty.data.RickMortyApiService
import com.srg.codetestrickmorty.data.features.locations.models.LastKnowLocationApiModel
import javax.inject.Inject

class LocationsDataSourceImpl @Inject constructor(private val remote: RickMortyApiService) :
    LocationsDataSource {

    override suspend fun getLastKnowLocation(locationId: Long): LastKnowLocationApiModel =
        try {
            remote.getLastKnowLocation(locationId)
        } catch (e: Throwable) {
            throw e.toDomain()
        }
}