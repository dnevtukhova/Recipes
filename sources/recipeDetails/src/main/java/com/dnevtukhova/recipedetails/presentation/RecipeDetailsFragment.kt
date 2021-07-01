package com.dnevtukhova.recipedetails.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.recipedetails.R
import com.dnevtukhova.recipedetails.databinding.RecipeDetailsFragmentBinding
import com.dnevtukhova.recipedetails.di.RecipeDetailsComponent
import com.dnevtukhova.ui_core.util.showSnackbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject


class RecipeDetailsFragment : Fragment() {

    companion object {
        private const val EXTRA_RESULT_KEY_RECIPE_DETAILS = "extra_result_key_recipe_details"
        fun getNewInstance(recipe: Recipe): RecipeDetailsFragment {
            return RecipeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_RESULT_KEY_RECIPE_DETAILS, recipe)
                }
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val recipesViewModel: RecipeDetailsViewModel
            by viewModels {
                viewModelFactory
            }

    private lateinit var binding: RecipeDetailsFragmentBinding
    private lateinit var recipe: Recipe
    private lateinit var ingredientsAdapter: RecipesDetailsAdapterIngredients
    private lateinit var stepsAdapter: RecipesDetailsAdapterSteps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecipeDetailsComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipeDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        recipe = bundle!!.getParcelable(EXTRA_RESULT_KEY_RECIPE_DETAILS)!!
        binding.title.text = recipe.title
        binding.readyInMinutesText.text = recipe.readyInMinutes + " min"
        binding.servingsText.text = recipe.servings + " servings"

        Glide.with(binding.imageRecipe.context)
            .load(recipe.image)
            .fitCenter()
            .circleCrop()
            .into(binding.imageRecipe)
        initRecycler()

        recipesViewModel.getCalories(recipeId = recipe.id)
        recipesViewModel.getIngredients(recipeId = recipe.id)

        binding.tabLayoutRecipeDetail.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when {
                    (binding.tabLayoutRecipeDetail.selectedTabPosition == 0) -> {
                        recipesViewModel.getIngredients(recipeId = recipe.id)
                    }
                    else -> {
                        recipesViewModel.getSteps(recipeId = recipe.id)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pie_chart -> recipesViewModel.openBarChartFragment(recipe.id)

        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        recipesViewModel.calories.observe(this.viewLifecycleOwner) {
            binding.kcalText.text = it.replace("k", " kcal")
        }

        recipesViewModel.ingredients.observe(this.viewLifecycleOwner) {
            ingredientsAdapter.setItems(it.ingredients)
        }

        recipesViewModel.listIngredientsVisibility.observe(this.viewLifecycleOwner) {
            binding.recyclerIngredients.isVisible = it
        }

        recipesViewModel.listStepsVisibility.observe(this.viewLifecycleOwner) {
            binding.recyclerSteps.isVisible = it
        }
        recipesViewModel.progress.observe(this.viewLifecycleOwner) {
            if (binding.progressRecipeDetail != null) {
                if (it) {
                    binding.progressRecipeDetail.isVisible = true
                } else {
                    binding.progressRecipeDetail.isGone = true
                }
            }
        }
        recipesViewModel.error.observe(this.viewLifecycleOwner) {
            requireView().showSnackbar(
                requireActivity().getString(it),
                Snackbar.LENGTH_INDEFINITE,
                requireActivity().getString(R.string.ok)
            ) {}
        }

        recipesViewModel.stepsCooking.observe(this.viewLifecycleOwner) {
            stepsAdapter.setItems(it.steps)
        }
    }

    private fun initRecycler() {
        val recyclerIngredients = binding.recyclerIngredients
        val layoutManager = GridLayoutManager(context, 2)
        recyclerIngredients.layoutManager = layoutManager
        ingredientsAdapter = RecipesDetailsAdapterIngredients()
        recyclerIngredients.adapter = ingredientsAdapter

        val recyclerSteps = binding.recyclerSteps
        val layoutManagerSteps = LinearLayoutManager(context)
        recyclerSteps.layoutManager = layoutManagerSteps
        stepsAdapter = RecipesDetailsAdapterSteps()
        recyclerSteps.adapter = stepsAdapter
    }
}