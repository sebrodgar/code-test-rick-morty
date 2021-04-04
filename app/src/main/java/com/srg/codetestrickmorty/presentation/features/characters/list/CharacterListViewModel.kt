package com.srg.codetestrickmorty.presentation.features.characters.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.srg.codetestrickmorty.common.base.BaseViewModel
import com.srg.codetestrickmorty.domain.features.characters.GetAllCharactersUseCase
import com.srg.codetestrickmorty.presentation.features.characters.mappers.toUi
import com.srg.codetestrickmorty.presentation.features.characters.models.CharacterUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private var currentCharactersResult: Flow<PagingData<CharacterUiModel>>? = null

    fun getCharacters(): Flow<PagingData<CharacterUiModel>> {
        viewModelScope.launch { }
        return getAllCharactersUseCase.execute()
            .map { pagingData ->
                pagingData.map { it.toUi() }
            }.cachedIn(viewModelScope).also { currentCharactersResult = it }
    }
}