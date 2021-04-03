package com.srg.codetestrickmorty.data.features.characters.sources

import com.srg.codetestrickmorty.common.db.RickMortyDatabase
import com.srg.codetestrickmorty.data.RickMortyApiService
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersDataSourceImplTest {

    private lateinit var source: CharactersDataSourceImpl

    @Mock
    private lateinit var remote: RickMortyApiService

    @Mock
    private lateinit var local: RickMortyDatabase

    @Before
    fun setUp() {
        source = CharactersDataSourceImpl(remote, local)
    }

}