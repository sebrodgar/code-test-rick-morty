package com.srg.codetestrickmorty.data.features.locations.sources

import com.srg.codetestrickmorty.data.features.locations.models.LastKnowLocationApiModel

interface LocationsDataSource {
    suspend fun getLastKnowLocation(locationId: Long): LastKnowLocationApiModel
}