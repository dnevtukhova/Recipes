package com.dnevtukhova.recipes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dnevtukhova.recipes.R
import com.dnevtukhova.recipes.data.api.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*
import java.util.*

class RecipesListAdapter(private val inflater: LayoutInflater) :
    RecyclerView.Adapter<RecipesListAdapter.RecipesViewHolder>() {
    private val items = ArrayList<Recipe>()

    fun setItems(stores: List<Recipe>) {
        items.clear()
        items.addAll(stores)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(inflater.inflate(R.layout.recipe_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class RecipesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val recipeImage = view.recipe_image
        private val recipeTitle = view.title_recipe
        private val recipeDishType = view.text_dish_type
        fun bind(recipe: Recipe) {
            recipeTitle.text = recipe.title
            recipeDishType.text = recipe.dishTypes.toString()
            Glide.with(itemView.context)
                .load(recipe.image)
                .centerCrop()
                .placeholder(R.drawable.ic_twotone_room_service_24)
                .into(recipeImage)
        }
    }
}