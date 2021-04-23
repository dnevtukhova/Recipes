package com.dnevtukhova.core_api.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface NavigationProvider {
    fun provideCicerone(): Cicerone<Router>
    fun provideRouter(): Router
    fun provideNavigatorHolder(): NavigatorHolder

}