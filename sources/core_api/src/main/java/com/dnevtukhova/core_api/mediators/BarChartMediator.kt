package com.dnevtukhova.core_api.mediators

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface BarChartMediator {
    fun startBarChartFragment(idRecipe: Long): FragmentScreen
}