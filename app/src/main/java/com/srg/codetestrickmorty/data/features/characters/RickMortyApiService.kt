package com.srg.codetestrickmorty.data.features.characters

import com.srg.codetestrickmorty.data.features.characters.models.CharacterApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface RickMortyApiService {

    @GET("/character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterApiResponse

}