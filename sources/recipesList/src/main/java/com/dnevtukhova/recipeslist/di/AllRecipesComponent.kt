package com.dnevtukhova.recipeslist.di

import com.dnevtukhova.core_api.ProvidersFacade
import com.dnevtukhova.recipeslist.presentation.AllRecipesListFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [RecipesModule::class]
)
@com.dnevtukhova.core_api.ActivityScope
interface AllRecipesComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): AllRecipesComponent {
            return DaggerAllRecipesComponent.builder()
                .providersFacade(providersFacade).build()
        }
    }

    fun inject(allRecipesListFragment: AllRecipesListFragment)
}