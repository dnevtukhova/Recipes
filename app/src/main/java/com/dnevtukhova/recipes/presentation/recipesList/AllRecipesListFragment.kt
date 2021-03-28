package com.dnevtukhova.recipes.presentation.recipesList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dnevtukhova.recipes.R
import com.dnevtukhova.recipes.data.api.Recipe
import com.dnevtukhova.recipes.presentation.MainActivity
import kotlinx.android.synthetic.main.all_recipes_fragment.*
import kotlinx.android.synthetic.main.all_recipes_fragment.view.*
import javax.inject.Inject

class AllRecipesListFragment : Fragment() {
    companion object {
        const val TAG = "AllRecipesListFragment"

            fun getNewInstance(): AllRecipesListFragment {
                return AllRecipesListFragment()
            }

    }

    lateinit var recipesAdapter: RecipesListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val recipesViewModel: RecipesViewModel
            by viewModels {
                viewModelFactory
            }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).recipesComponent.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_recipes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        recipesViewModel.recipesList.observe(this.viewLifecycleOwner) {
            recipesAdapter.setItems(it)
        }
        recipesViewModel.getPopularRecipesList()
        recipesViewModel.error.observe(this.viewLifecycleOwner) { error ->
            Toast.makeText(context, getString(error), Toast.LENGTH_LONG).show()
        }
        recipesViewModel.progress.observe(this.viewLifecycleOwner) {
            if (progress_all_recipes != null) {
                if (it) {
                    progress_all_recipes.isVisible = true
                } else {
                    progress_all_recipes.isGone = true
                }
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
            recipesViewModel.getPopularRecipesList()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initRecycler(view: View) {
        val recycler = view.recycler_view_recipes_list
        val layoutManager = GridLayoutManager(context, 1)
        recycler.layoutManager = layoutManager
        recipesAdapter = RecipesListAdapter(LayoutInflater.from(context), object: RecipesListAdapter.OnRecipeClickListener {
            override fun onRecipeClick(item: Recipe) {
                recipesViewModel.openDetailRecipeFragment()
            }
        })
        recycler.adapter = recipesAdapter
    }
}