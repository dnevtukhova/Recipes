package com.dnevtukhova.barchart.di

import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.barchart.presentation.BarChartViewModelFactory
import com.dnevtukhova.core_api.network.ServerApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class BarChartModule {
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
    abstract fun getViewModelFactory(barChartViewModelFactory: BarChartViewModelFactory): ViewModelProvider.Factory
}