package com.dnevtukhova.favoriteRecipes.presentation

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
import com.dnevtukhova.favoriteRecipes.databinding.FavoriteRecipeFragmentBinding
import com.dnevtukhova.favoriteRecipes.di.FavoriteRecipesComponent
import javax.inject.Inject

class FavoriteRecipesFragment : Fragment() {
    companion object {
        fun getNewInstance(): FavoriteRecipesFragment {
            return FavoriteRecipesFragment()
        }
    }

    private lateinit var binding: FavoriteRecipeFragmentBinding
    private lateinit var recipesAdapter: FavoriteRecipesListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val favoriteRecipesViewModel: FavoriteRecipesViewModel
            by viewModels {
                viewModelFactory
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FavoriteRecipesComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteRecipeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
        favoriteRecipesViewModel.getFavoriteRecipesList()
        binding.swipeRefreshLayout.setOnRefreshListener {
            favoriteRecipesViewModel.getFavoriteRecipesList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        favoriteRecipesViewModel.favoriteRecipesList.observe(this.viewLifecycleOwner) {
            recipesAdapter.setItems(it)
        }

        favoriteRecipesViewModel.error.observe(this.viewLifecycleOwner) { error ->
            Toast.makeText(context, getString(error), Toast.LENGTH_LONG).show()
        }
        favoriteRecipesViewModel.progress.observe(this.viewLifecycleOwner) {
            if (binding.progressAnimationView != null) {
                if (it) {
                    binding.progressAnimationView.isVisible = true
                } else {
                    binding.progressAnimationView.isGone = true
                }
            }
        }
    }

    private fun initRecycler() {
        val recycler = binding.recyclerViewRecipesListFavorite
        val layoutManager = GridLayoutManager(context, 1)
        recycler.layoutManager = layoutManager
        recipesAdapter =
            FavoriteRecipesListAdapter(object : FavoriteRecipesListAdapter.OnRecipeClickListener {
                override fun onRecipeClick(item: Recipe) {
                    favoriteRecipesViewModel.openDetailRecipeFragment(item)
                }

                override fun onCheckBoxClick(item: Recipe, isChecked: Boolean) {
                    favoriteRecipesViewModel.deleteFromFavorite(item.id)
                }
            })
        recycler.adapter = recipesAdapter
    }

}