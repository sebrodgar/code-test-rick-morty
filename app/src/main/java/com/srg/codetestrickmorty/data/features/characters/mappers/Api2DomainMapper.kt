package com.srg.codetestrickmorty.data.features.characters.mappers

import com.srg.codetestrickmorty.data.features.characters.models.*
import com.srg.codetestrickmorty.domain.features.characters.models.*


fun CharacterApiModel.toDomain() =
    CharacterDomainModel(
        id,
        name,
        status.toDomain(),
        species,
        type,
        gender.toDomain(),
        origin.toDomain(),
        location.toDomain(),
        image,
        url,
        created,
        isFav
    )

fun StatusApiModel.toDomain(): StatusDomainModel = when (this) {
    StatusApiModel.ALIVE -> StatusDomainModel.ALIVE
    StatusApiModel.DEAD -> StatusDomainModel.DEAD
    StatusApiModel.UNKNOWN -> StatusDomainModel.UNKNOWN
}

fun GenderApiModel.toDomain(): GenderDomainModel = when (this) {
    GenderApiModel.FEMALE -> GenderDomainModel.FEMALE
    GenderApiModel.MALE -> GenderDomainModel.MALE
    GenderApiModel.GENDERLESS -> GenderDomainModel.GENDERLESS
    GenderApiModel.UNKNOWN -> GenderDomainModel.UNKNOWN
}

fun OriginApiModel.toDomain() = OriginDomainModel(name, url)

fun LocationApiModel.toDomain() = LocationDomainModel(name, url)