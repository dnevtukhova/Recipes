package com.dnevtukhova.favoriteRecipes.navigation

import com.dnevtukhova.core_api.mediators.PreloaderMediator
import com.dnevtukhova.favoriteRecipes.presentation.FavoriteRecipesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class FavoriteRecipesMediatorImpl @Inject constructor(): PreloaderMediator {
    override fun startFavoriteRecipesFragment(): FragmentScreen {
        return FragmentScreen{ FavoriteRecipesFragment.getNewInstance()}
    }
}