package com.srg.codetestrickmorty.data.features.characters.models

import com.google.gson.annotations.SerializedName

enum class GenderApiModel {
    @SerializedName("Female")
    FEMALE,

    @SerializedName("Male")
    MALE,

    @SerializedName("Genderless")
    GENDERLESS,

    @SerializedName("unknown")
    UNKNOWN
}