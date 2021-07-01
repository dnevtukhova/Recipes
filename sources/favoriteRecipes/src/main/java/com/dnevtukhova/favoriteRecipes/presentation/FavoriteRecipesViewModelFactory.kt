package com.dnevtukhova.favoriteRecipes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class FavoriteRecipesViewModelFactory @Inject constructor(private val provider: Provider<FavoriteRecipesViewModel>) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return provider.get() as T
    }
}