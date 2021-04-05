package com.srg.codetestrickmorty.common.errors

@Suppress("MemberVisibilityCanBePrivate")
class NetworkException(
    val code: Int, val apiCode: APIErrorCode
) : RuntimeException("HTTP error code $code; Code: $apiCode")