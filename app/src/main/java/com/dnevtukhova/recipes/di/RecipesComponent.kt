package com.dnevtukhova.recipes.di

import com.dnevtukhova.recipes.presentation.AllRecipesListFragment
import com.dnevtukhova.recipes.presentation.MainActivity
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
            modules =[RecipesModule::class]
)
@ActivityScope
interface RecipesComponent {

    companion object {
        fun create(appComponent: AppComponent): RecipesComponent {
            return DaggerRecipesComponent.builder().appComponent(appComponent).build()
        }
    }
    fun inject(mainActivity: MainActivity)
    fun inject(allRecipesListFragment: AllRecipesListFragment)
}