package com.dnevtukhova.core

import com.dnevtukhova.core_api.navigation.NavigationProvider
import com.dnevtukhova.core_api.network.NetworkProvider
import com.dnevtukhova.core_impl.DaggerNavigationComponent
import com.dnevtukhova.core_impl.DaggerNetworkComponent

object CoreProvidersFactory {
    fun createNetworkBuilder(): NetworkProvider {
        return DaggerNetworkComponent.create()
    }

    fun createNavigationBuilder(): NavigationProvider {
        return DaggerNavigationComponent.create()
    }
}