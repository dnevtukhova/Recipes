package com.dnevtukhova.core_impl

import com.dnevtukhova.core_api.navigation.NavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent: NavigationProvider {
}