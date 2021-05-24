package com.dnevtukhova.recipedetails.di

import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.recipedetails.presentation.RecipesDetailsViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class RecipeDetailsModule {
    @Binds
    @com.dnevtukhova.core_api.ActivityScope
    abstract fun getViewModelFactory(recipesDetailsFactory: RecipesDetailsViewModelFactory): ViewModelProvider.Factory
}