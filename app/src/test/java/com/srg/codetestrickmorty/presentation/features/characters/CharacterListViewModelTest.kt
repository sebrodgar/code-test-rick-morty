package com.srg.codetestrickmorty.presentation.features.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.srg.codetestrickmorty.common.errors.APIErrorCode
import com.srg.codetestrickmorty.common.errors.NetworkException
import com.srg.codetestrickmorty.common.util.StateData
import com.srg.codetestrickmorty.domain.features.characters.AddCharacterFavUseCase
import com.srg.codetestrickmorty.domain.features.characters.GetAllCharactersUseCase
import com.srg.codetestrickmorty.presentation.features.characters.list.CharacterListViewModel
import com.srg.codetestrickmorty.utils.CoroutineTestRule
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
class CharacterListViewModelTest {

    private lateinit var viewModel: CharacterListViewModel

    @Mock
    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    @Mock
    private lateinit var addCharacterFavUseCase: AddCharacterFavUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CharacterListViewModel(
            getAllCharactersUseCase,
            addCharacterFavUseCase,
            coroutineTestRule.testCoroutineDispatcher
        )
    }

    @Test
    fun `it should call service add character fav use case and success interactions`() =
        coroutineTestRule.runBlockingTest {
            //given:
            val characterId = Random.nextLong()
            val isFav = true
            val params = AddCharacterFavUseCase.Params(isFav, characterId)

            addCharacterFavUseCase.stub { onBlocking { execute(params) } doReturn Unit }

            viewModel.setFav.observeForever(Observer {
                Assert.assertEquals(it is StateData.Content || it is StateData.Loading, true)
            })
            //when:
            viewModel.addCharacterFav(isFav, characterId)

            //then:
            verify(addCharacterFavUseCase).execute(params)

        }

    @Test
    fun `it should call service add character fav  use case and error interactions`() =
        coroutineTestRule.runBlockingTest {
            //given:
            val error = NetworkException(409, APIErrorCode.UNKNOWN)
            val characterId = Random.nextLong()
            val isFav = true
            val params = AddCharacterFavUseCase.Params(isFav, characterId)

            addCharacterFavUseCase.stub { onBlocking { execute(params) } doAnswer { throw error } }

            viewModel.setFav.observeForever(Observer {
                Assert.assertEquals(it is StateData.Error || it is StateData.Loading, true)
            })

            //when:
            viewModel.addCharacterFav(isFav, characterId)

            //then:
            verify(addCharacterFavUseCase).execute(params)
        }
}