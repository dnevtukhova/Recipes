package com.dnevtukhova.preloader.di

import com.dnevtukhova.core_api.ProvidersFacade
import com.dnevtukhova.preloader.presentation.PreloaderFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [PreloaderModule::class]
)
@com.dnevtukhova.core_api.ActivityScope
interface PreloaderComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade): PreloaderComponent {
            return DaggerPreloaderComponent.builder().providersFacade(providersFacade).build()
        }
    }

    fun inject(preloaderFragment: PreloaderFragment)
}