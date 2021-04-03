package com.srg.codetestrickmorty.presentation.features.characters.mappers

import com.srg.codetestrickmorty.domain.features.characters.models.*
import com.srg.codetestrickmorty.presentation.features.characters.models.*

fun CharacterDomainModel.toUi() =
    CharacterUiModel(
        name,
        status.toUi(),
        species,
        type,
        gender.toUi(),
        origin.toUi(),
        location.toUi(),
        image
    )

fun StatusDomainModel.toUi(): StatusUiModel = when (this) {
    StatusDomainModel.ALIVE -> StatusUiModel.ALIVE
    StatusDomainModel.DEAD -> StatusUiModel.DEAD
    StatusDomainModel.UNKNOWN -> StatusUiModel.UNKNOWN
}

fun GenderDomainModel.toUi(): GenderUiModel = when (this) {
    GenderDomainModel.FEMALE -> GenderUiModel.FEMALE
    GenderDomainModel.MALE -> GenderUiModel.MALE
    GenderDomainModel.GENDERLESS -> GenderUiModel.GENDERLESS
    GenderDomainModel.UNKNOWN -> GenderUiModel.UNKNOWN
}

fun OriginDomainModel.toUi() = OriginUiModel(url, name)

fun LocationDomainModel.toUi(): LocationUiModel {
    val urlSplit = url.split("https://rickandmortyapi.com/api/location/")
    val locationId = if (urlSplit.size < 2) null else urlSplit[1].toLong()
    return LocationUiModel(locationId, name)
}
