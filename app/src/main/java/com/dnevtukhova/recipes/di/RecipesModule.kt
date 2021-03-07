package com.dnevtukhova.recipes.di

import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.recipes.data.api.ServerApi
import com.dnevtukhova.recipes.presentation.RecipesListViewModelFactory
import dagger.*
import retrofit2.Retrofit

@Module
abstract class RecipesModule {

    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideServerApi(retrofit: Retrofit): ServerApi {
            return retrofit
                .create(ServerApi::class.java)
        }
    }

    @Binds
    @ActivityScope
    abstract fun getViewModelFactory(recipesListfactory: RecipesListViewModelFactory): ViewModelProvider.Factory
}