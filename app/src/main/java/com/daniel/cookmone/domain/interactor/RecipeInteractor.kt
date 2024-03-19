package com.daniel.cookmone.domain.interactor

import com.daniel.cookmone.data.network.RetrofitClient
import com.daniel.cookmone.data.repository.RecipeRepository
import com.daniel.cookmone.domain.RecipeResponseItem

class RecipeInteractor(): RecipeRepository() {
    override suspend fun getRecipes(): List<RecipeResponseItem> = RetrofitClient.getApiClient().getRecipes()
}