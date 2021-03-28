package com.dnevtukhova.recipes.di

import com.dnevtukhova.recipes.App
import com.dnevtukhova.recipes.presentation.recipeDetails.RecipeDetailsFragment
import dagger.Component

@Component(
    dependencies = [AppComponent::class
        ]
)
@ActivityScope
interface RecipeDetailsComponent {
    companion object {
        fun create(appComponent: AppComponent): RecipeDetailsComponent {
            return DaggerRecipeDetailsComponent.builder().appComponent(appComponent).build()
        }


        fun injectFragment(fragment: RecipeDetailsFragment): RecipeDetailsComponent {
            val component = create(
                (fragment.activity?.application
                        as App).getAppComponent()
            )
            component.inject(fragment)
            return component
        }
    }

    fun inject(recipeDetailsFragment: RecipeDetailsFragment)
}