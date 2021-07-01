package com.dnevtukhova.preloader.di

import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.preloader.presentation.PreloaderViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class PreloaderModule {

    @Binds
    @com.dnevtukhova.core_api.ActivityScope
    abstract fun getViewModelFactory(preloaderViewModelFactory: PreloaderViewModelFactory): ViewModelProvider.Factory
}