package com.dnevtukhova.recipes.di

import com.dnevtukhova.recipes.App
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
        fun injectActivity(activity: MainActivity): MainComponent {
            val component = create(
                (activity.application as App).getAppComponent()
            )
            component.inject(activity)
            return component
        }
    }
    fun inject(mainActivity: MainActivity)
}