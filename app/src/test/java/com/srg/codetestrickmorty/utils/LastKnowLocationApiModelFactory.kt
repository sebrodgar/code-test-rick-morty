package com.srg.codetestrickmorty.utils

import com.srg.codetestrickmorty.data.features.locations.models.LastKnowLocationApiModel
import java.time.LocalDateTime
import kotlin.random.Random

object LastKnowLocationApiModelFactory {
    fun createOne(): LastKnowLocationApiModel = LastKnowLocationApiModel(
        id = Random.nextLong(),
        name = Random.nextString(),
        type = Random.nextString(),
        dimension = Random.nextString(),
        residents = listOf(Random.nextString(), Random.nextString()),
        url = Random.nextString(),
        created = LocalDateTime.now()
    )
}