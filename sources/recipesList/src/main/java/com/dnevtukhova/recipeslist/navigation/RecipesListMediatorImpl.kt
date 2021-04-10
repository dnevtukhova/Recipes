package com.dnevtukhova.recipeslist.navigation

import com.dnevtukhova.core_api.mediators.RecipesListMediator
import com.dnevtukhova.recipeslist.presentation.AllRecipesListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class RecipesListMediatorImpl
@Inject constructor() : RecipesListMediator {

    override fun startRecipeListFragment() =
        FragmentScreen { AllRecipesListFragment.getNewInstance() }
}