package com.srg.codetestrickmorty.common.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.marvelFormat(): String =
    this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
