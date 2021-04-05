package com.srg.codetestrickmorty.presentation.features.characters.list

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.srg.codetestrickmorty.common.di.annotations.FragmentKey
import com.srg.codetestrickmorty.common.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CharacterListFragmentBuilder {
    @Binds
    @IntoMap
    @FragmentKey(CharacterListFragment::class)
    abstract fun bindCharacterListFragment(fragment: CharacterListFragment): Fragment


    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    abstract fun bindCharacterListViewModel(viewModel: CharacterListViewModel): ViewModel
}