package com.srg.codetestrickmorty.data.features.characters.db

import androidx.room.TypeConverter
import com.srg.codetestrickmorty.data.features.characters.models.GenderApiModel
import com.srg.codetestrickmorty.data.features.characters.models.StatusApiModel
import java.time.LocalDateTime

class Converters {

    @TypeConverter
    fun toStatus(value: String) = enumValueOf<StatusApiModel>(value)

    @TypeConverter
    fun fromStatus(value: StatusApiModel) = value.name

    @TypeConverter
    fun toGender(value: String) = enumValueOf<GenderApiModel>(value)

    @TypeConverter
    fun fromGender(value: GenderApiModel) = value.name

    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? = LocalDateTime.parse(dateString)

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? = date?.toString()

}