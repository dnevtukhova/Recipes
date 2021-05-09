package com.dnevtukhova.recipedetails.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dnevtukhova.core_api.dto.Instruction
import com.dnevtukhova.core_api.dto.Step
import com.dnevtukhova.recipedetails.R
import java.util.*

class RecipesDetailsAdapterSteps : RecyclerView.Adapter<StepsViewHolder>() {
    private val items = ArrayList<Step>()

    fun setItems(steps: List<Step>) {
        items.clear()
        items.addAll(steps)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        return StepsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.steps_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}

class StepsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val stepNumber = view.findViewById<TextView>(R.id.step_number)
    private val stepDescription = view.findViewById<TextView>(R.id.description)

    fun bind(steps: Step) {
        stepNumber.text = steps.number.toString()
        stepDescription.text = steps.step
    }
}