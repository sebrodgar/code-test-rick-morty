package com.srg.codetestrickmorty.data.features.characters.sources

import com.nhaarman.mockitokotlin2.*
import com.srg.codetestrickmorty.common.db.RickMortyDatabase
import com.srg.codetestrickmorty.data.RickMortyApiService
import com.srg.codetestrickmorty.data.features.characters.db.CharactersDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

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

    @Test
    fun `it should call service add character fav and success interactions`() = runBlocking {
        //given:
        val characterId = Random.nextLong()
        val isFav = true
        val charactersDao = mock<CharactersDao>()


        local.stub { onBlocking { charactersDao() } doReturn charactersDao }

        charactersDao.stub { onBlocking { addCharacterFav(isFav, characterId) } doReturn Unit }

        //when:
        val result = source.addCharacterFav(isFav, characterId)

        //then:
        verify(charactersDao).addCharacterFav(isFav, characterId)
        Assert.assertEquals(Unit, result)
    }

    @Test
    fun `it should call service add character fav  and error interactions`() = runBlocking {
        //given:
        val error = Throwable()
        val characterId = Random.nextLong()
        val isFav = true
        val charactersDao = mock<CharactersDao>()

        local.stub { onBlocking { charactersDao() } doReturn charactersDao }

        charactersDao.stub {
            onBlocking {
                addCharacterFav(
                    isFav,
                    characterId
                )
            } doAnswer { throw error }
        }

        //when:
        var result: Throwable? = null
        try {
            source.addCharacterFav(isFav, characterId)
        } catch (t: Throwable) {
            result = t
        }
        //then:
        verify(charactersDao).addCharacterFav(isFav, characterId)
        Assert.assertEquals(error, result)
    }

}