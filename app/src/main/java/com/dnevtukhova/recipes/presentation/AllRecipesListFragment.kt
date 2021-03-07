package com.dnevtukhova.recipes.presentation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnevtukhova.recipes.App
import com.dnevtukhova.recipes.R
import kotlinx.android.synthetic.main.all_recipes_fragment.view.*

class AllRecipesListFragment : Fragment() {
    companion object {
        const val TAG = "AllRecipesListFragment"
    }

    lateinit var recipesAdapter: RecipesListAdapter

    private val recipesViewModel by viewModels<RecipesViewModel> {
        RecipesListViewModelFactory(App.instance.recipesInteractor)
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

    }

    private fun initRecycler(view: View) {
        val recycler = view.recycler_view_recipes_list
        val layoutManager = GridLayoutManager(context, 1)
        recycler.layoutManager = layoutManager
        recipesAdapter = RecipesListAdapter(LayoutInflater.from(context))
        recycler.adapter = recipesAdapter
    }
}