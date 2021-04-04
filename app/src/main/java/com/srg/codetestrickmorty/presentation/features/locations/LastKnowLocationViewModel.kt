package com.srg.codetestrickmorty.presentation.features.locations

import androidx.lifecycle.viewModelScope
import com.srg.codetestrickmorty.common.base.BaseViewModel
import com.srg.codetestrickmorty.common.errors.toDialogError
import com.srg.codetestrickmorty.common.util.MutableLiveDataStatus
import com.srg.codetestrickmorty.domain.features.locations.GetLastKnowLocationUseCase
import com.srg.codetestrickmorty.domain.features.locations.models.LastKnowLocationDomainModel
import com.srg.codetestrickmorty.presentation.common.flow.collect
import com.srg.codetestrickmorty.presentation.common.flow.lceFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LastKnowLocationViewModel @Inject constructor(
    private val getLastKnowLocation: GetLastKnowLocationUseCase,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private var _lastKnowLocation = MutableLiveDataStatus<LastKnowLocationDomainModel>()
    val lastKnowLocation = _lastKnowLocation

    fun getLastKnowLocation(locationId: Long) {
        viewModelScope.launch(dispatcher) {
            lceFlow { getLastKnowLocation.execute(GetLastKnowLocationUseCase.Params(locationId)) }
                .collect(
                    onContent = {
                        _lastKnowLocation.onContent(it)
                    },
                    onError = {
                        _lastKnowLocation.onError(it.toDialogError())
                    },
                    onLoading = {
                        _lastKnowLocation.onLoading(it)
                    }
                )
        }
    }
}