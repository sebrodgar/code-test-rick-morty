package com.srg.codetestrickmorty.utils

import com.srg.codetestrickmorty.data.features.characters.models.LocationApiModel
import kotlin.random.Random

object LocationApiModelFactory {
    fun createOne(): LocationApiModel = LocationApiModel(
        name = Random.nextString(),
        url = Random.nextString()
    )
}