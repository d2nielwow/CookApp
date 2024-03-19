package com.daniel.cookmone.presentation.ui.view

import android.graphics.drawable.Drawable

internal class ToggleState (
    val textPaddingTop: Float,
    val textPaddingStart: Float,
    val textPaddingEnd: Float,
    val textPaddingBottom: Float,
    val firstText: String,
    val secondText: String,
    val textColor: Int,
    val activeBackground: Drawable,
    selectItemId: Int,
    val activeChangeDuration: Long
        ) {
    var selectItemId = SelectedItemMode.getMode(selectItemId)

    enum class SelectedItemMode(
        val id: Int
    ) {
        FIRST(0),
        SECOND(1);

        fun isFirst() = this == FIRST

        fun isSecond() = this == SECOND

        companion object{
            fun getMode(id: Int) = values().firstOrNull { it.id == id } ?: FIRST
        }
    }
}
