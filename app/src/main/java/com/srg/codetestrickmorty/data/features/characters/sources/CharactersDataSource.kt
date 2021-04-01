package com.srg.codetestrickmorty.data.features.characters.sources

import androidx.paging.PagingData
import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiModel
import kotlinx.coroutines.flow.Flow

interface CharactersDataSource {
    fun getAllCharacters(): Flow<PagingData<CharacterApiModel>>
}