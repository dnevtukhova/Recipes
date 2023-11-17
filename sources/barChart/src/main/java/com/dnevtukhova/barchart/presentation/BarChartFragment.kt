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
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dnevtukhova.barchart.R
import com.dnevtukhova.barchart.databinding.BarChartFragmentBinding
import com.dnevtukhova.barchart.di.BarChartComponent
import com.dnevtukhova.barchart.domain.State
import com.dnevtukhova.barchart.domain.model.BarChartModel
import com.dnevtukhova.barchart.domain.model.NutritionItem
import com.dnevtukhova.core_api.AppWithFacade
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
    ): View {
        binding = BarChartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val recipeId = bundle!!.getLong(EXTRA_RESULT_KEY_BAR_CHART)
        barChartViewModel.getNutritionWidget(recipeId = recipeId)
        initObservers()


    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                barChartViewModel.nutritionWidgetStateFlow.collect { state ->
                    when (state) {
                        is State.Error -> {
                            binding.progressBarChart.isVisible = false
                            Toast.makeText(context, state.error.toString(), Toast.LENGTH_LONG)
                                .show()
                        }

                        is State.Loading -> binding.progressBarChart.isVisible = true
                        is State.Success -> {
                            binding.progressBarChart.isVisible = false
                            initBarChart(state.data)
                            initFlowLayout(state.data)
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    private fun initBarChart(barChartModel: BarChartModel) {
        val items = mutableListOf<NutritionItem>()
        items.addAll(barChartModel.bad)
        items.addAll(barChartModel.good)

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
    private fun initFlowLayout(barChartModel: BarChartModel) {
        binding.calories.text = "${barChartModel.calories} ${getText(R.string.calories)}"
        binding.protein.text = "${getText(R.string.protein)} ${barChartModel.protein}"
        binding.totalFat.text = "${getText(R.string.total_fat)} ${barChartModel.fat}"
        binding.carbs.text = "${getText(R.string.carbs)} ${barChartModel.carbs}"
    }
}