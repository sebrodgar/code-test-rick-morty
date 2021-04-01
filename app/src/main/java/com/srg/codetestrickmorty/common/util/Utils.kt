package com.srg.codetestrickmorty.common.util

import java.util.*

inline fun <reified T : Enum<T>> fromEnum(enumValueStringName: String?, defaultValue: T) =
    if (enumValueStringName == null) defaultValue
    else {
        enumValues<T>().forEach { enumValue ->
            if (enumValue.toString().toUpperCase(Locale.getDefault())
                    .trim() == enumValueStringName.toUpperCase(
                    Locale.getDefault()
                )
                    .trim()
            ) {
                return enumValue
            }
        }
        defaultValue
    }