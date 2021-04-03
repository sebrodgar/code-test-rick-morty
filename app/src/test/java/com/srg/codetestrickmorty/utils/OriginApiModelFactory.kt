package com.srg.codetestrickmorty.utils

import com.srg.codetestrickmorty.data.features.characters.models.OriginApiModel
import kotlin.random.Random

object OriginApiModelFactory {
    fun createOne(): OriginApiModel = OriginApiModel(
        name = Random.nextString(),
        url = Random.nextString()
    )
}