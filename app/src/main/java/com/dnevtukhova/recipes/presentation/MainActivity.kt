package com.dnevtukhova.recipes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dnevtukhova.recipes.App
import com.dnevtukhova.recipes.R
import com.dnevtukhova.recipes.di.RecipesComponent

class MainActivity : AppCompatActivity() {
    lateinit var recipesComponent: RecipesComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
                recipesComponent = RecipesComponent.create((application as App).getAppComponent())
            recipesComponent.inject(this)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                AllRecipesListFragment(),
                AllRecipesListFragment.TAG
            )
            .commit()
    }
}