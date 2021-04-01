package com.srg.codetestrickmorty.common.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.srg.codetestrickmorty.common.util.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    @Suppress("MemberVisibilityCanBePrivate", "PropertyName")
    protected val navigation = SingleLiveEvent<NavDirections>()
}