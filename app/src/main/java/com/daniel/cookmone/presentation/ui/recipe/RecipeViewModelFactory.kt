package com.daniel.cookmone.presentation.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daniel.cookmone.domain.interactor.RecipeInteractor

class RecipeViewModelFactory(private val interactor: RecipeInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(interactor) as T
    }
}