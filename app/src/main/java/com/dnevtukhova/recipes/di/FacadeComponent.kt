package com.dnevtukhova.recipes.di

import android.app.Application
import com.dnevtukhova.core.CoreProvidersFactory
import com.dnevtukhova.core_api.AppProvider
import com.dnevtukhova.core_api.ProvidersFacade
import com.dnevtukhova.core_api.database.DataBaseProvider
import com.dnevtukhova.core_api.navigation.NavigationProvider
import com.dnevtukhova.core_api.network.NetworkProvider
import com.dnevtukhova.recipes.App
import dagger.Component

@Component(
    dependencies = [AppProvider::class, NavigationProvider::class, NetworkProvider::class, DataBaseProvider::class],
    modules = [MediatorsBindings::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .navigationProvider(CoreProvidersFactory.createNavigationBuilder())
                .networkProvider(CoreProvidersFactory.createNetworkBuilder())
                .dataBaseProvider(CoreProvidersFactory.createDatabaseBuilder(AppComponent.create(application)))
                .build()
    }

    fun inject(app: App)
}