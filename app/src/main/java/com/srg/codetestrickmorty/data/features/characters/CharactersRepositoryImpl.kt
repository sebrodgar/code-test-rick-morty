package com.srg.codetestrickmorty.data.features.characters

import androidx.paging.PagingData
import androidx.paging.map
import com.srg.codetestrickmorty.data.features.characters.mappers.toDomain
import com.srg.codetestrickmorty.data.features.characters.sources.CharactersDataSource
import com.srg.codetestrickmorty.domain.features.characters.CharactersRepository
import com.srg.codetestrickmorty.domain.features.characters.models.CharacterDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersDataSource: CharactersDataSource
) : CharactersRepository {

    override fun getAllCharacters(): Flow<PagingData<CharacterDomainModel>> =
        charactersDataSource.getAllCharacters().map { pagingData ->
            pagingData.map { it.toDomain() }
        }

    override suspend fun addCharacterFav(isFav: Boolean, characterId: Long) =
        charactersDataSource.addCharacterFav(isFav, characterId)
}