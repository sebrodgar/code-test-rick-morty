package com.srg.codetestrickmorty.data

import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiResponse
import com.srg.codetestrickmorty.data.features.locations.models.LastKnowLocationApiModel
import retrofit2.http.GET
import retrofit2.http.Query


interface RickMortyApiService {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterApiResponse

    @GET("location")
    suspend fun getLocationByCharacter(@Query("locationId") locationId: Long): LastKnowLocationApiModel

}