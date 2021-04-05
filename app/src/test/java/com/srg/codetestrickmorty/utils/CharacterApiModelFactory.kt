package com.srg.codetestrickmorty.utils

import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiModel
import com.srg.codetestrickmorty.data.features.characters.models.GenderApiModel
import com.srg.codetestrickmorty.data.features.characters.models.StatusApiModel
import java.time.LocalDateTime
import kotlin.random.Random

object CharacterApiModelFactory {
    fun createOne(): CharacterApiModel = CharacterApiModel(
        id = 0,
        name = Random.nextString(),
        status = StatusApiModel.ALIVE,
        species = Random.nextString(),
        type = Random.nextString(),
        gender = GenderApiModel.MALE,
        origin = OriginApiModelFactory.createOne(),
        location = LocationApiModelFactory.createOne(),
        image = Random.nextString(),
        url = Random.nextString(),
        created = LocalDateTime.now()
    )

    fun createSome(): List<CharacterApiModel> =
        (0..20).map { createOne() }

}