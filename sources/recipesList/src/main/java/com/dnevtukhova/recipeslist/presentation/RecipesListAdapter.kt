package com.dnevtukhova.recipeslist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.dnevtukhova.recipeslist.R
import java.util.*

class RecipesListAdapter(
    private val listener: OnRecipeClickListener
) :
    RecyclerView.Adapter<RecipesViewHolder>() {
    private val items = ArrayList<com.dnevtukhova.core_api.dto.Recipe>()

    fun setItems(stores: List<com.dnevtukhova.core_api.dto.Recipe>) {
        items.clear()
        items.addAll(stores)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener { listener.onRecipeClick(item) }
        holder.bind(item)

    }

    override fun getItemCount(): Int = items.size

    interface OnRecipeClickListener {
        fun onRecipeClick(item: com.dnevtukhova.core_api.dto.Recipe) {

        }
    }
}

class RecipesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val recipeImage = view.findViewById<ImageView>(R.id.recipe_image)
    private val recipeTitle = view.findViewById<TextView>(R.id.title_recipe)
    private val recipeDishType = view.findViewById<TextView>(R.id.text_dish_type)
    fun bind(recipe: com.dnevtukhova.core_api.dto.Recipe) {
        recipeTitle.text = recipe.title
        recipeDishType.text = recipe.dishTypes.toString()
        Glide.with(itemView.context)
            .load(recipe.image)
            .centerCrop()
            .placeholder(R.drawable.ic_twotone_room_service_24)
            .into(recipeImage)
    }
}
