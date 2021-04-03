package com.srg.codetestrickmorty

import androidx.appcompat.app.AppCompatActivity
import com.srg.codetestrickmorty.presentation.features.characters.list.CharacterListFragmentBuilder
import com.srg.codetestrickmorty.presentation.features.locations.LastKnowLocationFragmentBuilder
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class MainActivityBuilder {
    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            CharacterListFragmentBuilder::class,
            LastKnowLocationFragmentBuilder::class
        ]
    )
    abstract fun mainActivity(): MainActivity
}

@Module
abstract class MainActivityModule {
    @Binds
    abstract fun provideActivity(mainActivity: MainActivity): AppCompatActivity
}