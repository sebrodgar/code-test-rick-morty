package com.srg.codetestrickmorty.presentation.common.flow

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.srg.codetestrickmorty.common.errors.DialogErrorViewEntity
import com.srg.codetestrickmorty.common.util.StateData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

sealed class LceStatus<C> {
    data class Content<C>(val content: C) : LceStatus<C>()
    data class Error<C>(val error: Throwable) : LceStatus<C>()
    data class Loading<C>(val isLoading: Boolean) : LceStatus<C>()
}

@ExperimentalCoroutinesApi
fun <T> lceFlow(block: suspend () -> T): Flow<LceStatus<T>> =
    flow {
        emit(block())
    }.lceFlow()


@ExperimentalCoroutinesApi
fun <T> Flow<T>.lceFlow(): Flow<LceStatus<T>> =
    this.map {
        LceStatus.Content(it) as LceStatus<T>
    }.catch {
        emit(LceStatus.Error(it))
    }.onStart {
        emit(LceStatus.Loading(true))
    }.onCompletion {
        delay(100)
        emit(LceStatus.Loading(false))
    }


suspend fun <T> Flow<LceStatus<T>>.collect(
    onContent: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onLoading: ((Boolean) -> Unit)? = null
) {
    this.collect {
        when (it) {
            is LceStatus.Error -> onError.invoke(it.error)
            is LceStatus.Content -> onContent.invoke(it.content)
            is LceStatus.Loading -> onLoading?.invoke(it.isLoading)
        }
    }
}

fun <T> LiveData<StateData<T>>.observeLce(
    lifecycleOwner: LifecycleOwner,
    onContent: (T) -> Unit,
    onError: (DialogErrorViewEntity) -> Unit,
    onLoading: ((Boolean) -> Unit)?
) {
    this.observe(lifecycleOwner, Observer {
        when (it) {
            is StateData.Content -> onContent.invoke(it.content)
            is StateData.Error -> onError.invoke(it.error)
            is StateData.Loading -> onLoading?.invoke(it.loading)
        }
    })
}