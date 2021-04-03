package com.srg.codetestrickmorty.data.features.locations.sources

import com.srg.codetestrickmorty.data.RickMortyApiService
import com.srg.codetestrickmorty.data.features.locations.models.LastKnowLocationApiModel
import javax.inject.Inject

class LocationsDataSourceImpl @Inject constructor(private val remote: RickMortyApiService) :
    LocationsDataSource {

    override suspend fun getLastKnowLocation(locationId: Long): LastKnowLocationApiModel =
        remote.getLocationByCharacter(locationId)
}