package com.srg.codetestrickmorty.data.features.characters

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.srg.codetestrickmorty.data.features.characters.sources.CharactersDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersRepositoryImplTest {

    private lateinit var repository: CharactersRepositoryImpl

    @Mock
    private lateinit var datasource: CharactersDataSource

    @Before
    fun setUp() {
        repository = CharactersRepositoryImpl(datasource)
    }

    @Test
    fun `it should call service last know location and success interactions`() = runBlocking {
        //given:
        val characterId = Random.nextLong()
        val isFav = true

        datasource.stub { onBlocking { addCharacterFav(isFav, characterId) } doReturn Unit }

        //when:
        val result = repository.addCharacterFav(isFav, characterId)

        //then:
        verify(datasource).addCharacterFav(isFav, characterId)
        Assert.assertEquals(Unit, result)
    }

    @Test
    fun `it should call service last know location and error interactions`() = runBlocking {
        //given:
        val error = Throwable()
        val characterId = Random.nextLong()
        val isFav = true

        datasource.stub {
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
            repository.addCharacterFav(isFav, characterId)
        } catch (t: Throwable) {
            result = t
        }
        //then:
        verify(datasource).addCharacterFav(isFav, characterId)
        Assert.assertEquals(error, result)
    }

}