package com.dnevtukhova.barchart.di

import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.barchart.data.converter.BarChartConverter
import com.dnevtukhova.barchart.data.converter.NutritionItemConverter
import com.dnevtukhova.barchart.data.repository.BarChartRepositoryImpl
import com.dnevtukhova.barchart.domain.BarChartRepository
import com.dnevtukhova.barchart.domain.usecase.GetNutritionWidgetDataUseCase
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

        @JvmStatic
        @Provides
        @com.dnevtukhova.core_api.ActivityScope
        fun providesBarChartRepository(serverApi: ServerApi): BarChartRepository =
            BarChartRepositoryImpl(
                serverApi, BarChartConverter(
                    NutritionItemConverter()
                )
            )

        @JvmStatic
        @Provides
        @com.dnevtukhova.core_api.ActivityScope
        fun provideBarChartUseCase(repository: BarChartRepository): GetNutritionWidgetDataUseCase =
            GetNutritionWidgetDataUseCase(repository)
    }

    @Binds
    @com.dnevtukhova.core_api.ActivityScope
    abstract fun getViewModelFactory(barChartViewModelFactory: BarChartViewModelFactory): ViewModelProvider.Factory
}