package com.srg.codetestrickmorty.common.util.flow

abstract class UseCaseParamless<Output> {
    suspend fun execute(): Output = buildResult()

    protected abstract suspend fun buildResult(): Output
}