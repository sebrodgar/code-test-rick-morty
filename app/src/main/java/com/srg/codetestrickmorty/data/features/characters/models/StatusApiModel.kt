package com.srg.codetestrickmorty.data.features.characters.models

import com.google.gson.annotations.SerializedName

enum class StatusApiModel {
    @SerializedName("Alive")
    ALIVE,

    @SerializedName("Dead")
    DEAD,

    @SerializedName("unknown")
    UNKNOWN
}