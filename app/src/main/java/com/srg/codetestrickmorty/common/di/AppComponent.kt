package com.srg.codetestrickmorty.common.di

import com.srg.codetestrickmorty.common.CodeTestRickMortyApplication
import com.srg.codetestrickmorty.common.di.annotations.AppScope
import com.srg.codetestrickmorty.common.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AppModule::class,
        DataProvidersModule::class,
        DataSourceModule::class,
        RemoteModule::class,
        AndroidSupportInjectionModule::class,
        PresentationModule::class,
        RepositoriesModule::class
    ]
)
interface AppComponent : AndroidInjector<CodeTestRickMortyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CodeTestRickMortyApplication): Builder

        fun build(): AppComponent
    }
}
