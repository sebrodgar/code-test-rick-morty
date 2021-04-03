package com.srg.codetestrickmorty.domain.features.locations

import com.srg.codetestrickmorty.common.util.flow.UseCase
import com.srg.codetestrickmorty.domain.features.locations.models.LastKnowLocationDomainModel
import javax.inject.Inject

class GetLastKnowLocationByCharacter @Inject constructor(
    private val repository: LocationsRepository
) : UseCase<GetLastKnowLocationByCharacter.Params, LastKnowLocationDomainModel>() {

    data class Params(val locationId: Long)

    override suspend fun buildResult(params: Params): LastKnowLocationDomainModel =
        repository.getLastKnowLocation(params.locationId)
}