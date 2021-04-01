package com.srg.codetestrickmorty.common.util

import androidx.lifecycle.MutableLiveData
import com.srg.codetestrickmorty.common.errors.DialogErrorViewEntity

class MutableLiveDataStatus<T> : MutableLiveData<StateData<T>>() {
    fun onLoading(loading: Boolean) = postValue(StateData.Loading(loading))
    fun onContent(content: T) = postValue(StateData.Content(content))
    fun onError(error: DialogErrorViewEntity) = postValue(StateData.Error(error))
}
