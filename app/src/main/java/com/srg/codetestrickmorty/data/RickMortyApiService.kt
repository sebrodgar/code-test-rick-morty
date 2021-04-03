package com.srg.codetestrickmorty.data

import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiResponse
import com.srg.codetestrickmorty.data.features.locations.models.LastKnowLocationApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickMortyApiService {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterApiResponse

    @GET("location/{locationId}")
    suspend fun getLastKnowLocation(@Path("locationId") locationId: Long): LastKnowLocationApiModel

}