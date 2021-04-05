package com.srg.codetestrickmorty.data.features.locations.models

import java.time.LocalDateTime

data class LastKnowLocationApiModel(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: LocalDateTime
)
