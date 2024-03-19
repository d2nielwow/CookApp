package com.daniel.cookmone.presentation.ui.view

import android.content.Context
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.getDrawableOrThrow
import com.daniel.cookmone.R
import com.daniel.cookmone.databinding.ToogleViewBinding
import kotlin.math.max


class ToggleView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(
    context,attrs,
    R.attr.twoButtonStyle
){

    private val binding: ToogleViewBinding
    private var listener: OnButtonChangeListener? = null
    private val transition: AutoTransition
    private val state: ToggleState

    init {
        val array = context.obtainStyledAttributes(
            attrs,
            R.styleable.TwoButton,
            R.attr.twoButtonStyle,
            R.style.TwoButtonStyle
        )

       state = ToggleState(
           textPaddingTop = array.getDimension(R.styleable.TwoButton_tb_textPaddingTop, 0f),
           textPaddingStart = array.getDimension(R.styleable.TwoButton_tb_textPaddingStart, 0f),
           textPaddingEnd = array.getDimension(R.styleable.TwoButton_tb_textPaddingEnd, 0f),
           textPaddingBottom = array.getDimension(R.styleable.TwoButton_tb_textPaddingEnd, 0f),

           firstText = array.getString(R.styleable.TwoButton_tb_firstText).orEmpty(),
           secondText = array.getString(R.styleable.TwoButton_tb_secondText).orEmpty(),
           textColor = array.getColor(R.styleable.TwoButton_tb_textColor, 0),
           activeBackground = array.getDrawableOrThrow(R.styleable.TwoButton_tb_activeBackground),
           selectItemId = array.getInteger(R.styleable.TwoButton_tb_selectItem, 0),
           activeChangeDuration = array.getInteger(R.styleable.TwoButton_tb_activeChangeDuration,0).toLong()
       )

        val inflater = LayoutInflater.from(context)
        binding = ToogleViewBinding.inflate(inflater, this, true)

        transition = AutoTransition().apply {
            duration = state.activeChangeDuration
        }

        initViews()

    }

    private fun initViews() {
        with(binding){
            firstText.text = state.firstText
            secondText.text = state.secondText

            view.background = state.activeBackground

            firstText.setPadding(
                state.textPaddingStart.toInt(),
                state.textPaddingEnd.toInt(),
                state.textPaddingTop.toInt(),
                state.textPaddingBottom.toInt()
            )

            secondText.setPadding(
                state.textPaddingStart.toInt(),
                state.textPaddingEnd.toInt(),
                state.textPaddingTop.toInt(),
                state.textPaddingBottom.toInt()
            )

            firstText.setTextColor(state.textColor)
            secondText.setTextColor(state.textColor)

            firstText.setOnClickListener{
                if (!state.selectItemId.isFirst()){
                    applyChangeBias(0f)
                    state.selectItemId = ToggleState.SelectedItemMode.FIRST
                    listener?.onChange(0)
                }
            }

            secondText.setOnClickListener{
                if (!state.selectItemId.isSecond()){
                    applyChangeBias(1f)
                    state.selectItemId = ToggleState.SelectedItemMode.SECOND
                    listener?.onChange(1)
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        with(binding){
            val textWidth = max(firstText.measuredWidth, secondText.measuredWidth)

            if (firstText.measuredWidth != textWidth) {
                firstText.layoutParams.width = textWidth
                firstText.requestLayout()
            }

            if (secondText.measuredWidth != textWidth) {
                secondText.layoutParams.width = textWidth
                secondText.requestLayout()
            }
        }
    }

    private fun applyChangeBias(bias: Float, ignoreAnimation: Boolean = false){
        val set = ConstraintSet()
        set.clone(this)
        set.setHorizontalBias(R.id.view, bias)
        if (!ignoreAnimation) {
            TransitionManager.beginDelayedTransition(this, transition)
        }
        set.applyTo(this)
    }

     interface OnButtonChangeListener {
        fun onChange(selectedIndex: Int)
    }
}