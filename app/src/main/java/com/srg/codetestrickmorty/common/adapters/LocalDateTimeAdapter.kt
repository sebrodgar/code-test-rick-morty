package com.srg.codetestrickmorty.common.adapters

import com.google.gson.*
import java.lang.reflect.Type
import java.text.ParseException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter : JsonDeserializer<LocalDateTime?>,
    JsonSerializer<LocalDateTime?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime? {
        return try {
            val jsonStr = json.asJsonPrimitive.asString
            parseLocalDateTime(jsonStr)
        } catch (e: ParseException) {
            throw JsonParseException(e.message, e)
        }
    }

    @Throws(ParseException::class)
    private fun parseLocalDateTime(dateString: String?): LocalDateTime? {
        if (dateString == null || dateString.trim { it <= ' ' }.isEmpty()) {
            return null
        }
        return if (dateString.length == 10) {
            LocalDate.parse(dateString).atStartOfDay()
        } else LocalDateTime.parse(
            dateString,
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        )
    }

    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val strDateTime: String = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(src)
        return JsonPrimitive(strDateTime)
    }
}