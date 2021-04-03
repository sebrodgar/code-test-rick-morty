package com.srg.codetestrickmorty.data.features.locations.mappers

import com.srg.codetestrickmorty.data.features.locations.models.LastKnowLocationApiModel
import com.srg.codetestrickmorty.domain.features.locations.models.LastKnowLocationDomainModel


fun LastKnowLocationApiModel.toDomain() =
    LastKnowLocationDomainModel(id, name, type, dimension, residents, url, created)
