package com.daniel.cookmone.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeResponseItem(
    @SerializedName("author")   val author: String,
    @SerializedName("cook_time")   val cook_time: Int,
    @SerializedName("description")   val description: String,
    @SerializedName("energy")   val energy: Int,
    @SerializedName("fat")   val fat: Int,
    @SerializedName("id")   val id: Int,
    @SerializedName("image_url")   val image_url: String,
    @SerializedName("ingredients")   val ingredients: List<String>,
    @SerializedName("preparation_time")   val preparation_time: Int,
    @SerializedName("protein")   val protein: Int,
    @SerializedName("title")   val title: String
): Serializable