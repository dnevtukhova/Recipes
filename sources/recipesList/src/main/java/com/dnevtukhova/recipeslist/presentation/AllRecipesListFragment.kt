package com.dnevtukhova.recipeslist.presentation

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
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.core_api.dto.Recipe


import com.dnevtukhova.recipeslist.databinding.AllRecipesFragmentBinding
import com.dnevtukhova.recipeslist.di.AllRecipesComponent
import javax.inject.Inject

class AllRecipesListFragment : Fragment() {
    companion object {
        const val TAG = "AllRecipesListFragment"

        fun getNewInstance(): AllRecipesListFragment {
            return AllRecipesListFragment()
        }
    }

    private lateinit var binding: AllRecipesFragmentBinding
    private lateinit var recipesAdapter: RecipesListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val recipesViewModel: RecipesViewModel
            by viewModels {
                viewModelFactory
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AllRecipesComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AllRecipesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        recipesViewModel.recipesList.observe(this.viewLifecycleOwner) {
            recipesAdapter.setItems(it)
        }

        recipesViewModel.error.observe(this.viewLifecycleOwner) { error ->
            Toast.makeText(context, getString(error), Toast.LENGTH_LONG).show()
        }
        recipesViewModel.progress.observe(this.viewLifecycleOwner) {
            if (binding.progressAllRecipes != null) {
                if (it) {
                    binding.progressAllRecipes.isVisible = true
                } else {
                    binding.progressAllRecipes.isGone = true
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            recipesViewModel.getPopularRecipesList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initRecycler() {
        val recycler = binding.recyclerViewRecipesList
        val layoutManager = GridLayoutManager(context, 1)
        recycler.layoutManager = layoutManager
        recipesAdapter = RecipesListAdapter(object : RecipesListAdapter.OnRecipeClickListener {
            override fun onRecipeClick(item: Recipe) {
                recipesViewModel.openDetailRecipeFragment(item)
            }
        })
        recycler.adapter = recipesAdapter
    }
}