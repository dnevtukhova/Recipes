package com.dnevtukhova.recipes.di

import com.dnevtukhova.recipes.presentation.MainActivity
import dagger.Component

@Component(
    dependencies = [AppComponent::class]
)
@ActivityScope
interface MainComponent {
    companion object {
        fun create(appComponent: AppComponent): MainComponent {
            return DaggerMainComponent.builder().appComponent(appComponent).build()
        }
    }
    fun inject(mainActivity: MainActivity)
}