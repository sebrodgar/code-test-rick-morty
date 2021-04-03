package com.srg.codetestrickmorty.data.features.locations

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.srg.codetestrickmorty.data.features.locations.mappers.toDomain
import com.srg.codetestrickmorty.data.features.locations.sources.LocationsDataSource
import com.srg.codetestrickmorty.utils.LastKnowLocationApiModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocationsRepositoryImplTest {

    private lateinit var repository: LocationsRepositoryImpl

    @Mock
    private lateinit var datasource: LocationsDataSource

    @Before
    fun setUp() {
        repository = LocationsRepositoryImpl(datasource)
    }

    @Test
    fun `it should call service last know location and success interactios`() = runBlocking {
        //given:
        val response = LastKnowLocationApiModelFactory.createOne()
        val locationId = 20L

        datasource.stub { onBlocking { getLastKnowLocation(locationId = locationId) } doReturn response }

        //when:
        val result = repository.getLastKnowLocation(locationId)

        //then:
        verify(datasource).getLastKnowLocation(locationId)
        Assert.assertEquals(response.toDomain(), result)
    }

    @Test
    fun `it should call service last know location and error interactions`() = runBlocking {
        //given:
        val error = Throwable()
        val locationId = 20L

        datasource.stub { onBlocking { getLastKnowLocation(locationId) } doAnswer { throw error } }

        //when:
        var result: Throwable? = null
        try {
            repository.getLastKnowLocation(locationId)
        } catch (t: Throwable) {
            result = t
        }
        //then:
        verify(datasource).getLastKnowLocation(locationId)
        Assert.assertEquals(error, result)
    }

}