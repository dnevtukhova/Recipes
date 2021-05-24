package com.dnevtukhova.recipedetails.di

import com.dnevtukhova.core_api.ProvidersFacade
import com.dnevtukhova.recipedetails.presentation.RecipeDetailsFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [RecipeDetailsModule::class]
)
@com.dnevtukhova.core_api.ActivityScope
interface RecipeDetailsComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade): RecipeDetailsComponent {
            return DaggerRecipeDetailsComponent.builder().providersFacade(providersFacade).build()
        }
    }

    fun inject(recipeDetailsFragment: RecipeDetailsFragment)
}