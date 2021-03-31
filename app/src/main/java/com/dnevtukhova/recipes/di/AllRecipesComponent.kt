package com.dnevtukhova.recipes.di

import com.dnevtukhova.recipes.App
import com.dnevtukhova.recipes.presentation.recipesList.AllRecipesListFragment
import dagger.Component

@Component(
    dependencies = [AppComponent::class
       ],
    modules = [RecipesModule::class]
)
@ActivityScope
interface AllRecipesComponent {

    companion object {
        fun create(appComponent: AppComponent): AllRecipesComponent {
            return DaggerAllRecipesComponent.builder().appComponent(appComponent).build()
        }


        fun injectFragment(fragment: AllRecipesListFragment): AllRecipesComponent {
            val component = create(
                (fragment.activity?.application
                        as App).getAppComponent()
            )
            component.inject(fragment)
            return component
        }
    }


    fun inject(allRecipesListFragment: AllRecipesListFragment)
}