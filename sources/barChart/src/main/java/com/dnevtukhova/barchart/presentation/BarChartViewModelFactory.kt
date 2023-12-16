package com.dnevtukhova.barchart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class BarChartViewModelFactory  @Inject constructor(private val provider: Provider<BarChartViewModel>) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.get() as T
    }

}