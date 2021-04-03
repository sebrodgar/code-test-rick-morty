package com.srg.codetestrickmorty.data.features.characters.models

data class CharacterApiResponse(
    val info: RickMortyApi,
    val results: List<CharacterApiModel>
)
