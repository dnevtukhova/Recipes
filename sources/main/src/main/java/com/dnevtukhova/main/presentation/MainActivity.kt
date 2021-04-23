package com.dnevtukhova.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.core_api.mediators.RecipesListMediator
import com.dnevtukhova.main.R
import com.dnevtukhova.main.di.MainComponent

import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var filmsListMediator: RecipesListMediator

    private val navigator: Navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
      //  MainComponent.injectActivity(this)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
     router.replaceScreen(filmsListMediator.startRecipeListFragment())

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