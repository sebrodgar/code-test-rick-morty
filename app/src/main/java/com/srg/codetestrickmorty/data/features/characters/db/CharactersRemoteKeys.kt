package com.srg.codetestrickmorty.data.features.characters.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DbConstans.REMOTE_KEYS_TABLE_NAME)
data class CharactersRemoteKeys(
    @PrimaryKey
    val characterId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
