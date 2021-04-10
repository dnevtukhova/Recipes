package com.dnevtukhova.recipedetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.recipedetails.R
import com.dnevtukhova.recipedetails.di.RecipeDetailsComponent


class RecipeDetailsFragment : Fragment() {
    companion object {
        fun getNewInstance(): RecipeDetailsFragment {
            return RecipeDetailsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecipeDetailsComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipe_details_fragment, container, false)
    }

}