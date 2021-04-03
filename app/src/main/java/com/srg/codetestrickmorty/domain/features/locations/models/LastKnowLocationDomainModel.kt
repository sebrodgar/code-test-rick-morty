package com.srg.codetestrickmorty.domain.features.locations.models

import java.time.LocalDateTime

data class LastKnowLocationDomainModel(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: LocalDateTime
)
