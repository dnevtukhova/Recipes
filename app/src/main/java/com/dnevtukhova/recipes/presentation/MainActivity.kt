package com.dnevtukhova.recipes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dnevtukhova.recipes.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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