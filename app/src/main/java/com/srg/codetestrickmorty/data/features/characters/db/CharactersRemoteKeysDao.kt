package com.srg.codetestrickmorty.data.features.characters.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharactersRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(remoteKeys: List<CharactersRemoteKeys>)

    @Query("SELECT * FROM ${DbConstans.REMOTE_KEYS_TABLE_NAME} WHERE ${DbConstans.REMOTE_KEYS_CHARACTER_ID} = :characterId")
    suspend fun remoteKeysCharacterId(characterId: Long): CharactersRemoteKeys?

    @Query("DELETE FROM ${DbConstans.REMOTE_KEYS_TABLE_NAME}")
    suspend fun clearRemoteKeys()
}