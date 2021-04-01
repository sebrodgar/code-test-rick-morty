package com.srg.codetestrickmorty.common.base

import android.os.Bundle
import com.srg.codetestrickmorty.common.di.injections.FragmentInjectionFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var fragmentFactory: FragmentInjectionFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}