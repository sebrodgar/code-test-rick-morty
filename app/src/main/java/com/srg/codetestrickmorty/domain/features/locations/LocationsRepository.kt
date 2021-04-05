package com.srg.codetestrickmorty.domain.features.locations

import com.srg.codetestrickmorty.domain.features.locations.models.LastKnowLocationDomainModel

interface LocationsRepository {
    suspend fun getLastKnowLocation(locationId: Long): LastKnowLocationDomainModel
}