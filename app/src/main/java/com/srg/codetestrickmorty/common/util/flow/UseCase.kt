package com.srg.codetestrickmorty.common.util.flow

abstract class UseCase<in Params, Output> {
    suspend fun execute(params: Params): Output = buildResult(params)

    protected abstract suspend fun buildResult(params: Params): Output
}