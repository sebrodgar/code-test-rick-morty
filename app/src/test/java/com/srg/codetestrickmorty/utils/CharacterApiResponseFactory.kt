package com.srg.codetestrickmorty.utils

import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiModel
import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiResponse
import com.srg.codetestrickmorty.data.features.characters.models.GenderApiModel
import com.srg.codetestrickmorty.data.features.characters.models.StatusApiModel
import java.time.LocalDateTime
import kotlin.random.Random

object CharacterApiResponseFactory {
    fun createOne(): CharacterApiResponse = CharacterApiResponse(
        info = RickMortyApiFactory.createOne(),
        results = CharacterApiModelFactory.createSome()
    )
}