package com.srg.codetestrickmorty.data.features.characters.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiModel

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes: List<CharacterApiModel>)

    @Query("SELECT * FROM ${DbConstans.CHARACTERS_TABLE_NAME}")
    fun getAllCharacters(): PagingSource<Int, CharacterApiModel>

    @Query("DELETE FROM ${DbConstans.CHARACTERS_TABLE_NAME}")
    suspend fun clearCharacters()
}