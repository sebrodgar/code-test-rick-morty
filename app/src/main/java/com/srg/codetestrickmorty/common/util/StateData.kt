package com.srg.codetestrickmorty.common.util

import com.srg.codetestrickmorty.common.errors.DialogErrorViewEntity

sealed class StateData<T> {
    data class Loading<T>(var loading: Boolean) : StateData<T>()
    data class Content<T>(var content: T) : StateData<T>()
    data class Error<T>(var error: DialogErrorViewEntity) : StateData<T>()
}