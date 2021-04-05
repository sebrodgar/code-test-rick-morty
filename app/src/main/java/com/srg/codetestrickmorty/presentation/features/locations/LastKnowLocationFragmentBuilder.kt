package com.srg.codetestrickmorty.presentation.features.locations

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.srg.codetestrickmorty.common.di.annotations.FragmentKey
import com.srg.codetestrickmorty.common.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LastKnowLocationFragmentBuilder {
    @Binds
    @IntoMap
    @FragmentKey(LastKnowLocationFragment::class)
    abstract fun bindLastKnowLocationFragment(fragment: LastKnowLocationFragment): Fragment


    @Binds
    @IntoMap
    @ViewModelKey(LastKnowLocationViewModel::class)
    abstract fun bindLastKnowLocationViewModel(viewModel: LastKnowLocationViewModel): ViewModel
}