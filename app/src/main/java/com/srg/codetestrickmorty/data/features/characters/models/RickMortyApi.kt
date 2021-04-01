package com.srg.codetestrickmorty.data.features.characters.models

data class RickMortyApi(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
