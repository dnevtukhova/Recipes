package com.dnevtukhova.barchart.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.google.android.material.internal.ViewUtils.dpToPx


class BarChart(context: Context, attributeSet: AttributeSet) : ViewGroup(context, attributeSet) {

    data class Bar(
        val nameView: View,
        val barView: View,
        val labelView: View,
        val value: Float
    )

    private val bars = mutableListOf<Bar>()

    @SuppressLint("RestrictedApi")
    private val barMarginH = dpToPx(context, 4)

    @SuppressLint("RestrictedApi")
    private val barMarginV = dpToPx(context, 2)

    fun add(bar: Bar) {
        bars.add(bar)

        addChildInternal(bar.nameView)
        addChildInternal(bar.barView)
        addChildInternal(bar.labelView)
    }

    private fun addChildInternal(child: View) {
        addView(child)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val captionMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        val labelMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)

        for (bar in bars) {
            bar.nameView.measure(captionMeasureSpec, captionMeasureSpec)
            bar.labelView.measure(labelMeasureSpec, labelMeasureSpec)
        }

        val maxCaptionHeight = bars.map { it.nameView.measuredHeight }.maxOrNull() ?: 0

        setMeasuredDimension(
            MeasureSpec.getSize(widthMeasureSpec),
            maxCaptionHeight * bars.size
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (bars.size == 0) {
            return
        }

        val itemHeight = (b - t) / bars.size

        val nameRight = bars.map { it.nameView.measuredWidth }.maxOrNull() ?: 0
        val maxValue = bars.map { it.value }.maxOrNull() ?: 0.0F

        for ((index, bar) in bars.withIndex()) {
            val nameLeft = nameRight - bar.nameView.measuredWidth
            val nameTop = index * itemHeight
            val nameBottom = nameTop + itemHeight

            bar.nameView.layout(
                nameLeft,
                nameTop,
                nameRight,
                nameBottom
            )

            var barWidth = 0
            if (maxValue > 0.0) {
                barWidth = (bar.value * (r - nameRight - barMarginH * 2) / maxValue).toInt()
            }

            val barTop = index * itemHeight
            val barRight = nameRight + barWidth + barMarginH * 2
            val barBottom = barTop + itemHeight
            bar.barView.layout(
                nameRight + barMarginH.toInt(),
                barTop + barMarginV.toInt(),
                barRight.toInt() - barMarginH.toInt(),
                barBottom - barMarginV.toInt()
            )

            val labelWidth = bar.labelView.measuredWidth
            val spaceLeftForLabel = barWidth - 2 * barMarginH

            val labelLeft = if (spaceLeftForLabel >= labelWidth) {
                barRight - labelWidth - barMarginH
            } else {
                barRight
            }

            val labelRight = labelLeft + labelWidth

            bar.labelView.layout(
                labelLeft.toInt(),
                barTop,
                labelRight.toInt(),
                barBottom
            )
        }
    }
}