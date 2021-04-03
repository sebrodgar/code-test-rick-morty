package com.srg.codetestrickmorty.common.util.flow

abstract class UseCaseParamlessFlow<Output> {
    fun execute(): Output = buildResult()

    protected abstract fun buildResult(): Output
}