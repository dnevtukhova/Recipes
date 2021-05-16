package com.dnevtukhova.favoriteRecipes.di

import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.favoriteRecipes.presentation.FavoriteRecipesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FavoriteRecipesModule {

    @Binds
    @com.dnevtukhova.core_api.ActivityScope
    abstract fun getViewModelFactory(favoriteRecipesViewModelFactory: FavoriteRecipesViewModelFactory): ViewModelProvider.Factory
}