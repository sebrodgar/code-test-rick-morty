package com.srg.codetestrickmorty.presentation.features.characters.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.srg.codetestrickmorty.common.base.BaseViewModel
import com.srg.codetestrickmorty.common.errors.toDialogError
import com.srg.codetestrickmorty.common.util.MutableLiveDataStatus
import com.srg.codetestrickmorty.domain.features.characters.AddCharacterFavUseCase
import com.srg.codetestrickmorty.domain.features.characters.GetAllCharactersUseCase
import com.srg.codetestrickmorty.presentation.common.flow.collect
import com.srg.codetestrickmorty.presentation.common.flow.lceFlow
import com.srg.codetestrickmorty.presentation.features.characters.mappers.toUi
import com.srg.codetestrickmorty.presentation.features.characters.models.CharacterUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val addCharacterFavUseCase: AddCharacterFavUseCase,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private var _setFav = MutableLiveDataStatus<Unit>()
    val setFav = _setFav

    fun getCharacters(): Flow<PagingData<CharacterUiModel>> {
        viewModelScope.launch { }
        return getAllCharactersUseCase.execute()
            .map { pagingData ->
                pagingData.map { it.toUi() }
            }
    }

    fun addCharacterFav(isFav: Boolean, characterId: Long) {
        viewModelScope.launch(dispatcher) {
            lceFlow {
                addCharacterFavUseCase.execute(AddCharacterFavUseCase.Params(isFav, characterId))
            }.collect(
                onContent = {
                    _setFav.onContent(it)
                },
                onError = {
                    _setFav.onError(it.toDialogError())
                },
                onLoading = {
                    _setFav.onLoading(it)
                }
            )
        }
    }
}