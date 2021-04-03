package com.srg.codetestrickmorty.utils

import com.srg.codetestrickmorty.data.features.characters.models.RickMortyApi
import kotlin.random.Random

object RickMortyApiFactory {
    fun createOne(): RickMortyApi = RickMortyApi(
        count = Random.nextInt(),
        pages = Random.nextInt(0, 100),
        next = Random.nextString(),
        prev = Random.nextString()
    )
}