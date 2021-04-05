package com.srg.codetestrickmorty.data.features.characters.sources

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.srg.codetestrickmorty.common.db.RickMortyDatabase
import com.srg.codetestrickmorty.data.RickMortyApiService
import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val remote: RickMortyApiService,
    private val local: RickMortyDatabase
) : CharactersDataSource {

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    @ExperimentalPagingApi
    override fun getAllCharacters(): Flow<PagingData<CharacterApiModel>> {
        val pagingSourceFactory = { local.charactersDao().getAllCharacters() }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = CharactersRemoteMediator(remote = remote, local = local),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun addCharacterFav(isFav: Boolean, characterId: Long) =
        local.charactersDao().addCharacterFav(isFav, characterId)

}