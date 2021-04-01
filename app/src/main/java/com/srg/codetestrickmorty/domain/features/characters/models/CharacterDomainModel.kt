package com.srg.codetestrickmorty.domain.features.characters.models

import java.time.LocalDateTime

data class CharacterDomainModel(
    val id: Long,
    val name: String,
    val status: StatusDomainModel,
    val species: String,
    val type: String,
    val gender: GenderDomainModel,
    val origin: OriginDomainModel,
    val location: LocationDomainModel,
    val image: String,
    val url: String,
    val created: LocalDateTime
)
