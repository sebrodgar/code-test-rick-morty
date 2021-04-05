package com.srg.codetestrickmorty.domain.features.characters

import androidx.paging.PagingData
import com.srg.codetestrickmorty.common.util.flow.UseCaseParamlessFlow
import com.srg.codetestrickmorty.domain.features.characters.models.CharacterDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) : UseCaseParamlessFlow<Flow<PagingData<CharacterDomainModel>>>() {

    override fun buildResult(): Flow<PagingData<CharacterDomainModel>> =
        repository.getAllCharacters()
}