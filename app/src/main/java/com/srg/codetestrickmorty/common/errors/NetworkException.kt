package com.srg.codetestrickmorty.common.errors

@Suppress("MemberVisibilityCanBePrivate")
class NetworkException(
    val code: Int, val marvelCode: APIErrorCode
) : RuntimeException("HTTP error code $code; Code: $marvelCode")