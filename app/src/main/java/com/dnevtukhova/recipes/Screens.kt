package com.dnevtukhova.recipes

import com.dnevtukhova.recipes.presentation.recipeDetails.RecipeDetailsFragment
import com.dnevtukhova.recipes.presentation.recipesList.AllRecipesListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun RecipeDetails() = FragmentScreen {
        RecipeDetailsFragment.getNewInstance()
    }

    fun AllRecipes() = FragmentScreen {
        AllRecipesListFragment.getNewInstance()
    }
}