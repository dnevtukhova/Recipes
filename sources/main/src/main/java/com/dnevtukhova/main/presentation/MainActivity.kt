package com.dnevtukhova.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.core_api.mediators.PreloaderMediator
import com.dnevtukhova.core_api.mediators.RecipesListMediator
import com.dnevtukhova.main.R
import com.dnevtukhova.main.di.MainComponent
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var preloaderMediator: PreloaderMediator

    @Inject
    lateinit var recipesListMediator: RecipesListMediator

    private val navigator: Navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        router.replaceScreen(recipesListMediator.startRecipeListFragment())

        setBottomNavigation()

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun setBottomNavigation() {
        val bar: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.all_recipes -> {
                    router.newRootScreen(recipesListMediator.startRecipeListFragment())
                }
                R.id.recipes_favorite -> {
                    router.newRootScreen(preloaderMediator.startFavoriteRecipesFragment())
                }
                R.id.recipes_categories -> {
                    router.newRootScreen(recipesListMediator.startRecipeListFragment())
                }
            }
            true
        }
    }
}