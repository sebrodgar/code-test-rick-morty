package com.srg.codetestrickmorty.data.features.characters.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.srg.codetestrickmorty.data.features.characters.db.DbConstans
import java.time.LocalDateTime

@Entity(tableName = DbConstans.CHARACTERS_TABLE_NAME)
data class CharacterApiModel(
    @PrimaryKey
    val id: Long,
    val name: String,
    val status: StatusApiModel,
    val species: String,
    val type: String,
    val gender: GenderApiModel,
    @Embedded(prefix = "origin_") val origin: OriginApiModel,
    @Embedded(prefix = "location_") val location: LocationApiModel,
    val image: String,
    val url: String,
    val created: LocalDateTime,
    val isFav: Boolean = false
)