package com.srg.codetestrickmorty.data.features.characters

import com.srg.codetestrickmorty.data.features.characters.sources.CharactersDataSourceImpl
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersRepositoryImplTest {

    private lateinit var repository: CharactersRepositoryImpl

    @Mock
    private lateinit var dataSource: CharactersDataSourceImpl

    @Before
    fun setUp() {
        repository = CharactersRepositoryImpl(dataSource)
    }


}