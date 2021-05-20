package com.dnevtukhova.recipedetails.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.core_api.dto.Recipe
import com.dnevtukhova.recipedetails.R
import com.dnevtukhova.recipedetails.databinding.RecipeDetailsFragmentBinding
import com.dnevtukhova.recipedetails.di.RecipeDetailsComponent
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
        binding.title.text = recipe!!.title
        binding.readyInMinutesText.text = recipe.readyInMinutes + " min"
        binding.servingsText.text = recipe.servings + " servings"
        binding.summaryText.text = recipe.summary

        Glide.with(binding.imageRecipe.context)
            .load(recipe.image)
            .centerCrop()
            .circleCrop()
            .into(binding.imageRecipe)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
           R.id.pie_chart -> recipesViewModel.openBarChartFragment(recipe.id)

        }
        return  super.onOptionsItemSelected(item)
    }

}