package com.srg.codetestrickmorty.domain.features.characters

import androidx.paging.PagingData
import com.srg.codetestrickmorty.domain.features.characters.models.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getAllCharacters(): Flow<PagingData<CharacterDomainModel>>
    suspend fun addCharacterFav(isFav: Boolean, characterId: Long)

}