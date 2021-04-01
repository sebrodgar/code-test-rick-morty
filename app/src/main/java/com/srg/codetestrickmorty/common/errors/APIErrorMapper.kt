package com.srg.codetestrickmorty.common.errors

import com.google.gson.GsonBuilder
import com.srg.codetestrickmorty.common.util.fromEnum

inline fun <reified T> parseHttpError(httpCode: Int, errorBody: String) =
    GsonBuilder().create().getAdapter(APIError::class.java).fromJson(
        errorBody
    ).run {
        NetworkException(
            httpCode,
            fromEnum(this?.code, APIErrorCode.UNKNOWN)
        )
    }
