package com.srg.codetestrickmorty.presentation.features.characters.models

data class CharacterUiModel(
    val id: Long,
    val name: String,
    val status: StatusUiModel,
    val species: String,
    val type: String,
    val gender: GenderUiModel,
    val origin: OriginUiModel,
    val location: LocationUiModel,
    val image: String,
    var isFav: Boolean
)
