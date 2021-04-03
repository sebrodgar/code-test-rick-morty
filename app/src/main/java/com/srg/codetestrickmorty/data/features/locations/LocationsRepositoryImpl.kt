package com.srg.codetestrickmorty.data.features.locations

import com.srg.codetestrickmorty.data.features.locations.mappers.toDomain
import com.srg.codetestrickmorty.data.features.locations.sources.LocationsDataSource
import com.srg.codetestrickmorty.domain.features.locations.LocationsRepository
import com.srg.codetestrickmorty.domain.features.locations.models.LastKnowLocationDomainModel
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(private val dataSource: LocationsDataSource) :
    LocationsRepository {

    override suspend fun getLastKnowLocation(locationId: Long): LastKnowLocationDomainModel =
        dataSource.getLastKnowLocation(locationId).toDomain()
}