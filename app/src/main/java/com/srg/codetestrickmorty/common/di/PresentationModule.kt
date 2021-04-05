package com.srg.codetestrickmorty.common.di

import com.srg.codetestrickmorty.MainActivityBuilder
import dagger.Module

@Module(
    includes = [MainActivityBuilder::class]
)
class PresentationModule