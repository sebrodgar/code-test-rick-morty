package com.srg.codetestrickmorty.presentation.features

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.srg.codetestrickmorty.common.errors.APIErrorCode
import com.srg.codetestrickmorty.common.errors.NetworkException
import com.srg.codetestrickmorty.common.util.StateData
import com.srg.codetestrickmorty.data.features.locations.mappers.toDomain
import com.srg.codetestrickmorty.domain.features.locations.GetLastKnowLocationUseCase
import com.srg.codetestrickmorty.presentation.features.locations.LastKnowLocationViewModel
import com.srg.codetestrickmorty.utils.CoroutineTestRule
import com.srg.codetestrickmorty.utils.LastKnowLocationApiModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LastKnowLocationViewModelTest {

    private lateinit var viewModel: LastKnowLocationViewModel

    @Mock
    private lateinit var useCase: GetLastKnowLocationUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = LastKnowLocationViewModel(useCase, coroutineTestRule.testCoroutineDispatcher)
    }

    @Test
    fun `it should call service get last know location use case and success interactios`() =
        coroutineTestRule.runBlockingTest {
            //given:
            val location = LastKnowLocationApiModelFactory.createOne().toDomain()
            val locationId = Random.nextLong()

            val params = GetLastKnowLocationUseCase.Params(locationId)
            useCase.stub { onBlocking { execute(params) } doReturn location }

            viewModel.lastKnowLocation.observeForever(Observer {
                Assert.assertEquals(it is StateData.Content || it is StateData.Loading, true)
            })
            //when:
            viewModel.getLastKnowLocation(locationId)

            //then:
            verify(useCase).execute(params)

        }

    @Test
    fun `it should call serviceget last know location use case and error interactions`() =
        coroutineTestRule.runBlockingTest {
            //given:
            val error = NetworkException(409, APIErrorCode.UNKNOWN)
            val locationId = Random.nextLong()
            val params = GetLastKnowLocationUseCase.Params(locationId)
            useCase.stub { onBlocking { execute(params) } doAnswer { throw error } }

            viewModel.lastKnowLocation.observeForever(Observer {
                Assert.assertEquals(it is StateData.Error || it is StateData.Loading, true)
            })

            //when:
            viewModel.getLastKnowLocation(locationId)

            //then:
            verify(useCase).execute(params)

        }
}