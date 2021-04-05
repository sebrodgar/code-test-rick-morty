package com.srg.codetestrickmorty.domain.features.characters

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
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
class AddCharacterFavUseCaseTest {
    private lateinit var useCase: AddCharacterFavUseCase

    @Mock
    private lateinit var repository: CharactersRepository

    @Before
    fun setUp() {
        useCase = AddCharacterFavUseCase(repository)
    }

    @Test
    fun `it should call service get character use case and success interactions`() = runBlocking {
        //given:
        val characterId = Random.nextLong()
        val isFav = true
        repository.stub { onBlocking { addCharacterFav(isFav, characterId) } doReturn Unit }

        //when:
        val result = repository.addCharacterFav(isFav, characterId)

        //then:
        verify(repository).addCharacterFav(isFav, characterId)
        Assert.assertEquals(Unit, result)
    }

    @Test
    fun `it should call service get character use case and error interactions`() = runBlocking {
        //given:
        val error = Throwable()
        val characterId = Random.nextLong()
        val isFav = true
        repository.stub {
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
        verify(repository).addCharacterFav(isFav, characterId)
        Assert.assertEquals(error, result)
    }
}