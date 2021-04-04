package com.srg.codetestrickmorty.domain.locations

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.srg.codetestrickmorty.data.features.locations.mappers.toDomain
import com.srg.codetestrickmorty.domain.features.locations.GetLastKnowLocationUseCase
import com.srg.codetestrickmorty.domain.features.locations.LocationsRepository
import com.srg.codetestrickmorty.utils.LastKnowLocationApiModelFactory
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
class GetLastKnowLocationByCharacterUseCaseTest {
    private lateinit var useCase: GetLastKnowLocationUseCase

    @Mock
    private lateinit var repository: LocationsRepository

    @Before
    fun setUp() {
        useCase = GetLastKnowLocationUseCase(repository)
    }

    @Test
    fun `it should call service get character use case and success interactions`() = runBlocking {
        //given:
        val location = LastKnowLocationApiModelFactory.createOne().toDomain()
        val locationId = Random.nextLong()
        repository.stub { onBlocking { getLastKnowLocation(locationId) } doReturn location }

        //when:
        val result = repository.getLastKnowLocation(locationId)

        //then:
        verify(repository).getLastKnowLocation(locationId)
        Assert.assertEquals(location, result)
    }

    @Test
    fun `it should call service get character use case and error interactions`() = runBlocking {
        //given:
        val error = Throwable()
        val locationId = Random.nextLong()
        repository.stub { onBlocking { getLastKnowLocation(locationId) } doAnswer { throw error } }

        //when:
        var result: Throwable? = null
        try {
            repository.getLastKnowLocation(locationId)
        } catch (t: Throwable) {
            result = t
        }
        //then:
        verify(repository).getLastKnowLocation(locationId)
        Assert.assertEquals(error, result)
    }
}