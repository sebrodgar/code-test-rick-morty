package com.srg.codetestrickmorty.domain.features.characters

import com.srg.codetestrickmorty.common.util.flow.UseCase
import javax.inject.Inject

class AddCharacterFavUseCase @Inject constructor(
    private val repository: CharactersRepository
) : UseCase<AddCharacterFavUseCase.Params, Unit>() {

    data class Params(val isFav: Boolean, val characterId: Long)

    override suspend fun buildResult(params: Params) =
        repository.addCharacterFav(params.isFav, params.characterId)
}