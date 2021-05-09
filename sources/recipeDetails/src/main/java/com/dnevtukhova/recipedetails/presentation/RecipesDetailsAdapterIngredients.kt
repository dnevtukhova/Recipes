package com.dnevtukhova.recipedetails.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dnevtukhova.core_api.dto.Ingredient
import com.dnevtukhova.recipedetails.R
import com.dnevtukhova.recipedetails.domain.NetworkConstants.URL_LOAD_PICTURE
import java.util.*

class RecipesDetailsAdapterIngredients :
    RecyclerView.Adapter<IngredientsViewHolder>() {
    private val items = ArrayList<Ingredient>()

    fun setItems(ingredients: List<Ingredient>) {
        items.clear()
        items.addAll(ingredients)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ingredients_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}

class IngredientsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val ingredientImage = view.findViewById<ImageView>(R.id.image_ingredient)
    private val ingredientName = view.findViewById<TextView>(R.id.ingredient_name)
    private val ingredientMass = view.findViewById<TextView>(R.id.ingredient_count)

    @SuppressLint("SetTextI18n")
    fun bind(ingredient: Ingredient) {
        ingredientName.text = ingredient.name
        ingredientMass.text = "${ingredient.amount.metric.value} ${ingredient.amount.metric.unit}"

        Glide.with(ingredientImage.context)
            .load("$URL_LOAD_PICTURE${ingredient.image}")
            .fitCenter()
            .into(ingredientImage)
    }
}