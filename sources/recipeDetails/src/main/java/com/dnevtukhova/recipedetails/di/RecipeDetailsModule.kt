package com.dnevtukhova.recipedetails.di

import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.core_api.network.ServerApi
import com.dnevtukhova.recipedetails.presentation.RecipesDetailsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class RecipeDetailsModule {
    companion object {
        @JvmStatic
        @Provides
        @com.dnevtukhova.core_api.ActivityScope
        fun provideServerApi(retrofit: Retrofit): ServerApi {
            return retrofit
                .create(ServerApi::class.java)
        }
    }

    @Binds
    @com.dnevtukhova.core_api.ActivityScope
    abstract fun getViewModelFactory(recipesDetailsFactory: RecipesDetailsViewModelFactory): ViewModelProvider.Factory
}