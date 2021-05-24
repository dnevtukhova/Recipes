package com.dnevtukhova.recipedetails.presentation

import androidx.lifecycle.ViewModel
import com.dnevtukhova.core_api.mediators.BarChartMediator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(
    private val router: Router,
    private val barChartMediator: BarChartMediator
) : ViewModel() {
    fun openBarChartFragment(recipeId: Long) {
        router.navigateTo(barChartMediator.startBarChartFragment(idRecipe = recipeId))
    }
}