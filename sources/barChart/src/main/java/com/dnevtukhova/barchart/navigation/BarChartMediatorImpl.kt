package com.dnevtukhova.barchart.navigation

import com.dnevtukhova.barchart.presentation.BarChartFragment
import com.dnevtukhova.core_api.mediators.BarChartMediator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class BarChartMediatorImpl @Inject constructor(): BarChartMediator {
    override fun startBarChartFragment(idRecipe: Long): FragmentScreen {
        return FragmentScreen { BarChartFragment.getNewInstance(idRecipe) }
    }
}