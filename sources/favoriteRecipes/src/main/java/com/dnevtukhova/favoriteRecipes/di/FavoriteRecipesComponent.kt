package com.dnevtukhova.favoriteRecipes.di

import com.dnevtukhova.core_api.ProvidersFacade
import com.dnevtukhova.favoriteRecipes.presentation.FavoriteRecipesFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [FavoriteRecipesModule::class]
)
@com.dnevtukhova.core_api.ActivityScope
interface FavoriteRecipesComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade): FavoriteRecipesComponent {
            return DaggerFavoriteRecipesComponent.builder().providersFacade(providersFacade).build()
        }
    }

    fun inject(favoriteRecipesFragment: FavoriteRecipesFragment)
}