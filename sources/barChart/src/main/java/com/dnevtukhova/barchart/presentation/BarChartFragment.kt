package com.dnevtukhova.barchart.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.barchart.R
import com.dnevtukhova.barchart.databinding.BarChartFragmentBinding
import com.dnevtukhova.barchart.di.BarChartComponent
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.core_api.dto.NutritionItem
import com.dnevtukhova.core_api.dto.NutritionWidget
import javax.inject.Inject

class BarChartFragment : Fragment() {

    companion object {
        const val TAG = "BarChartFragment"
        private const val EXTRA_RESULT_KEY_BAR_CHART = "extra_result_key_bar_chart"
        fun getNewInstance(recipeId: Long): BarChartFragment {
            return BarChartFragment().apply {
                arguments = Bundle().apply {
                    putLong(EXTRA_RESULT_KEY_BAR_CHART, recipeId)
                }
            }
        }
    }

    private lateinit var binding: BarChartFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val barChartViewModel: BarChartViewModel
            by viewModels {
                viewModelFactory
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarChartComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BarChartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val recipeId = bundle!!.getLong(EXTRA_RESULT_KEY_BAR_CHART)!!
        barChartViewModel.getNutritionWidget(recipeId = recipeId)
        initObservers()


    }

    private fun initObservers() {
        barChartViewModel.nutritionWidget.observe(this.viewLifecycleOwner) {
            initBarChart(it)
            initFlowLayout(it)
        }

        barChartViewModel.progress.observe(this.viewLifecycleOwner) {
            if (binding.progressBarChart!= null) {
                if (it) {
                    binding.progressBarChart.isVisible = true
                } else {
                    binding.progressBarChart.isGone = true
                }
            }
        }

        barChartViewModel.error.observe(this.viewLifecycleOwner) {error ->
            Toast.makeText(context, getString(error), Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    private fun initBarChart(nutritionWidget: NutritionWidget) {
        val items = mutableListOf<NutritionItem>()
        items.addAll(nutritionWidget.bad)
        items.addAll(nutritionWidget.good)

        val defaultPaddingH = 2
        val defaultPaddingV = 5
        for (i in items) {
            val nameView = TextView(this.requireContext())
            nameView.text = i.title
            nameView.setTypeface(nameView.typeface, Typeface.BOLD)
            nameView.typeface = ResourcesCompat.getFont(requireContext(), R.font.poppins_regular)
            nameView.setPadding(defaultPaddingH, defaultPaddingV, defaultPaddingH, defaultPaddingV)

            val barView = View(this.requireContext())
            barView.setBackgroundColor(Color.rgb(210, 152, 81))
            barView.setPadding(defaultPaddingH, defaultPaddingV, defaultPaddingH, defaultPaddingV)

            val labelView = TextView(this.requireContext())
            labelView.text = "${i.percentOfDailyNeeds}%"
            labelView.setTypeface(nameView.typeface, Typeface.BOLD)
            labelView.setPadding(defaultPaddingH, defaultPaddingV, defaultPaddingH, defaultPaddingV)

            binding.barChart.add(BarChart.Bar(nameView, barView, labelView, i.percentOfDailyNeeds))
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initFlowLayout(nutritionWidget: NutritionWidget) {
        binding.calories.text = "${nutritionWidget.calories} ${getText(R.string.calories)}"
        binding.protein.text = "${getText(R.string.protein)} ${nutritionWidget.protein}"
        binding.totalFat.text = "${getText(R.string.total_fat)} ${nutritionWidget.fat}"
        binding.carbs.text = "${getText(R.string.carbs)} ${nutritionWidget.carbs}"
    }
}