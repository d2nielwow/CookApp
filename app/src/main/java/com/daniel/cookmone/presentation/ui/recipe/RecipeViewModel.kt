package com.daniel.cookmone.presentation.ui.recipe

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniel.cookmone.domain.RecipeResponseItem
import com.daniel.cookmone.domain.interactor.RecipeInteractor
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val interactor: RecipeInteractor
) : ViewModel() {

    val recipeListLiveData = MutableLiveData<List<RecipeResponseItem>>()

     fun getRecipes(){
         viewModelScope.launch {
             try {
                 val response = interactor.getRecipes()
                 recipeListLiveData.value = response
             }
             catch (e:Exception){
                 Log.d("main","getRecipe: ${e.message}")
             }
         }
    }
}