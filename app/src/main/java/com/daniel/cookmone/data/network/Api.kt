package com.daniel.cookmone.data.network

import com.daniel.cookmone.domain.RecipeResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("recipes")
    fun getRecipes(): Call<List<RecipeResponseItem>>
}