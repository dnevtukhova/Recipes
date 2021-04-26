package com.dnevtukhova.barchart.di

import com.dnevtukhova.barchart.presentation.BarChartFragment
import com.dnevtukhova.core_api.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [BarChartModule::class]
)
@com.dnevtukhova.core_api.ActivityScope
interface BarChartComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade): BarChartComponent {
            return DaggerBarChartComponent.builder().providersFacade(providersFacade).build()
        }
    }

    fun inject(barChartFragment: BarChartFragment)
}