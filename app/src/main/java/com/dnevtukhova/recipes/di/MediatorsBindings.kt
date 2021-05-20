package com.dnevtukhova.recipes.di

import com.dnevtukhova.barchart.navigation.BarChartMediatorImpl
import com.dnevtukhova.core_api.mediators.BarChartMediator
import com.dnevtukhova.core_api.mediators.RecipeDetailsMediator
import com.dnevtukhova.core_api.mediators.RecipesListMediator
import com.dnevtukhova.core_api.mediators.MainMediator
import com.dnevtukhova.main.naavigation.MainMediatorImpl
import com.dnevtukhova.recipedetails.navigation.RecipeDetailsMediatorImpl
import com.dnevtukhova.recipeslist.navigation.RecipesListMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MediatorsBindings {
    @Binds
    @Reusable
    fun bindsMainMediator(mainMediatorImpl: MainMediatorImpl): MainMediator

    @Binds
    @Reusable
    fun bindsRecipesListMediator(recipesListMediatorImpl: RecipesListMediatorImpl): RecipesListMediator

    @Binds
    @Reusable
    fun bindsRecipeDetailsMediator(recipeDetailsMediatorImpl: RecipeDetailsMediatorImpl): RecipeDetailsMediator

    @Binds
    @Reusable
    fun bindsBarChartMediator(barChartMediatorImpl: BarChartMediatorImpl): BarChartMediator
}