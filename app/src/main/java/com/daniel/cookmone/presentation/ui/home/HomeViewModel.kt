package com.daniel.cookmone.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniel.cookmone.data.network.RetrofitClient
import com.daniel.cookmone.domain.RecipeResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val recipeListLiveData = MutableLiveData<List<RecipeResponseItem>>()
    val errorLiveData = MutableLiveData<String>()

    init {
        getData()
    }

    private fun getData(){
        RetrofitClient.getApiClient().getRecipes()
            .enqueue(object : Callback<List<RecipeResponseItem>>{
                override fun onResponse(
                    call: Call<List<RecipeResponseItem>>,
                    response: Response<List<RecipeResponseItem>>
                ) {
                    if (response.isSuccessful && response.body() != null){
                        val list = response.body()!!
                        recipeListLiveData.postValue(list)
                    }
                }

                override fun onFailure(call: Call<List<RecipeResponseItem>>, t: Throwable) {
                    errorLiveData.postValue(t.localizedMessage)
                }
            })
    }
}