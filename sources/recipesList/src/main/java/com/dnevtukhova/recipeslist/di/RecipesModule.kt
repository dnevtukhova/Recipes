package com.dnevtukhova.recipeslist.di

import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.core_api.network.ServerApi
import com.dnevtukhova.recipeslist.presentation.RecipesListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit


@Module
abstract class RecipesModule {

    companion object {
        @JvmStatic
        @Provides
        @com.dnevtukhova.core_api.ActivityScope
        fun provideServerApi(retrofit: Retrofit): ServerApi {
            return retrofit
                .create(ServerApi::class.java)
        }
        @JvmStatic
        @Provides
        @com.dnevtukhova.core_api.ActivityScope
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Default
    }

    @Binds
    @com.dnevtukhova.core_api.ActivityScope
    abstract fun getViewModelFactory(recipesListFactory: RecipesListViewModelFactory): ViewModelProvider.Factory
}