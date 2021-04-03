package com.srg.codetestrickmorty.data.features.locations.sources

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.srg.codetestrickmorty.data.RickMortyApiService
import com.srg.codetestrickmorty.utils.LastKnowLocationApiModelFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationsDataSourceImplTest {

    private lateinit var source: LocationsDataSourceImpl

    @Mock
    private lateinit var remote: RickMortyApiService

    @Before
    fun setUp() {
        source = LocationsDataSourceImpl(remote)
    }

    @Test
    fun `it should call service last know location and success interactios`() = runBlocking {
        //given:
        val response = LastKnowLocationApiModelFactory.createOne()
        val locationId = 20L

        remote.stub { onBlocking { getLastKnowLocation(locationId = locationId) } doReturn response }

        //when:
        val result = source.getLastKnowLocation(locationId)

        //then:
        verify(remote).getLastKnowLocation(locationId)
        Assert.assertEquals(response, result)
    }

    @Test
    fun `it should call service last know location and error interactions`() = runBlocking {
        //given:
        val error = Throwable()
        val locationId = 20L

        remote.stub { onBlocking { getLastKnowLocation(locationId) } doAnswer { throw error } }

        //when:
        var result: Throwable? = null
        try {
            source.getLastKnowLocation(locationId)
        } catch (t: Throwable) {
            result = t
        }
        //then:
        verify(remote).getLastKnowLocation(locationId)
        Assert.assertEquals(error, result)
    }

}