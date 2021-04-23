package com.dnevtukhova.recipes

import android.app.Application
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.core_api.ProvidersFacade
import com.dnevtukhova.recipes.di.FacadeComponent

open class App : Application(), AppWithFacade {
    companion object {
        private var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }
}