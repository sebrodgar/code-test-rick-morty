package com.srg.codetestrickmorty.data.features.characters.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiModel

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(recipes: List<CharacterApiModel>)

    @Query("SELECT * FROM ${DbConstans.CHARACTERS_TABLE_NAME}")
    fun getAllCharacters(): PagingSource<Int, CharacterApiModel>

    @Query("UPDATE ${DbConstans.CHARACTERS_TABLE_NAME} SET ${DbConstans.CHARACTERS_FAV_FIELD} = :isFav WHERE ${DbConstans.CHARACTERS_ID_FIELD} == :characterId")
    suspend fun addCharacterFav(isFav: Boolean, characterId: Long)

    @Query("DELETE FROM ${DbConstans.CHARACTERS_TABLE_NAME}")
    suspend fun clearCharacters()
}