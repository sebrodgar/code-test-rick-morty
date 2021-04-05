package com.srg.codetestrickmorty.data.features.characters.sources

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.srg.codetestrickmorty.common.db.RickMortyDatabase
import com.srg.codetestrickmorty.data.RickMortyApiService
import com.srg.codetestrickmorty.data.features.characters.db.CharactersRemoteKeys
import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiModel
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CharactersRemoteMediator(
    private val remote: RickMortyApiService,
    private val local: RickMortyDatabase
) : RemoteMediator<Int, CharacterApiModel>() {
    companion object {
        private const val RICKMORTY_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterApiModel>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getCharacterRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: RICKMORTY_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getCharacterRemoteKeyForFirstItem(state)
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                val remoteKeys = getCharacterRemoteKeyForLastItem(state)
                remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }

        try {
            val apiResponse = remote.getAllCharacters(page)

            val characters = apiResponse.results
            val endOfPaginationReached = characters.isEmpty()

            local.withTransaction {

                val prevKey = if (page == RICKMORTY_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = characters.map {
                    CharactersRemoteKeys(characterId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                local.charactersRemoteKeysDao().insertAll(keys)
                local.charactersDao().insertAll(characters)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getCharacterRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterApiModel>
    ): CharactersRemoteKeys? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { characterId ->
                local.charactersRemoteKeysDao().remoteKeysCharacterId(characterId)
            }
        }

    private suspend fun getCharacterRemoteKeyForFirstItem(state: PagingState<Int, CharacterApiModel>): CharactersRemoteKeys? =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                local.charactersRemoteKeysDao().remoteKeysCharacterId(character.id)
            }

    private suspend fun getCharacterRemoteKeyForLastItem(state: PagingState<Int, CharacterApiModel>): CharactersRemoteKeys? =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                local.charactersRemoteKeysDao().remoteKeysCharacterId(character.id)
            }
}