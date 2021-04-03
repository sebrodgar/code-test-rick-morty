package com.srg.codetestrickmorty.presentation.features.characters.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.srg.codetestrickmorty.common.base.BaseViewModel
import com.srg.codetestrickmorty.domain.features.characters.GetAllCharactersUseCase
import com.srg.codetestrickmorty.domain.features.characters.models.CharacterDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private var currentCharactersResult: Flow<PagingData<CharacterDomainModel>>? = null

    fun getCharacters(): Flow<PagingData<CharacterDomainModel>> {
        viewModelScope.launch { }
        return getAllCharactersUseCase.execute()
            .cachedIn(viewModelScope).also { currentCharactersResult = it }
    }
}