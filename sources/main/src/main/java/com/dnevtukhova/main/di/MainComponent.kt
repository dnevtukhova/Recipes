package com.dnevtukhova.main.di

import com.dnevtukhova.core_api.ProvidersFacade
import com.dnevtukhova.main.presentation.MainActivity

import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class]
)
@com.dnevtukhova.core_api.ActivityScope
interface MainComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade): MainComponent {
            return DaggerMainComponent.builder()
              //  .navigationProvider(CoreProvidersfactory.createNavigationBuilder())
              //  .networkProvider(CoreProvidersfactory.createNetworkBuilder())
                .providersFacade(providersFacade).build()
        }
//        fun injectActivity(activity: com.dnevtukhova.main.presentation.MainActivity): MainComponent {
//            val component = create(
//                (activity.application as App).getAppComponent()
//            )
//            component.inject(activity)
//            return component
//        }
    }
    fun inject(mainActivity: MainActivity)
}