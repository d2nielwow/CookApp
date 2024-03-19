package com.daniel.cookmone.data.repository

import com.daniel.cookmone.data.network.Api
import com.daniel.cookmone.data.network.RetrofitClient
import com.daniel.cookmone.domain.RecipeResponseItem

open class RecipeRepository(): Api {
    override suspend fun getRecipes(): List<RecipeResponseItem> = RetrofitClient.getApiClient().getRecipes()
}