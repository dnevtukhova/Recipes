package com.dnevtukhova.recipes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dnevtukhova.recipes.App
import com.dnevtukhova.recipes.R
import com.dnevtukhova.recipes.Screens.AllRecipes
import com.dnevtukhova.recipes.di.RecipesComponent
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var recipesComponent: RecipesComponent

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator: Navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recipesComponent = RecipesComponent.create((application as App).getAppComponent())
        recipesComponent.inject(this)
        router.replaceScreen(AllRecipes())

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}