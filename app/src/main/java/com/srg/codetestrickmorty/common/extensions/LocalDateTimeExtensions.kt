package com.srg.codetestrickmorty.common.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.simpleFormat(): String =
    this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
