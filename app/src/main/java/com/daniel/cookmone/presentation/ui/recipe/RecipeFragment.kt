package com.daniel.cookmone.presentation.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.daniel.cookmone.R
import com.daniel.cookmone.domain.RecipeResponseItem
import com.daniel.cookmone.domain.interactor.RecipeInteractor
import com.daniel.cookmone.presentation.ui.recipe.detail.RecipeDetailFragment

class RecipeFragment : Fragment(), RecipeAdapter.OnActionListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    private lateinit var recycler: RecyclerView

    private val adapter by lazy { RecipeAdapter(this) }
    private val interactor =  RecipeInteractor()
    private val viewModelFactory = RecipeViewModelFactory(interactor)
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.rv_menu)
        recycler.adapter = adapter

        recipeViewModel = ViewModelProvider(this, viewModelFactory)[RecipeViewModel::class.java]
        recipeViewModel.getRecipes()
        recipeViewModel.recipeListLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)

        }
    }

    companion object {
        fun newInstance() = RecipeFragment()
    }

    override fun onItemClicked(menu: RecipeResponseItem) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, RecipeDetailFragment.newInstance(menu))
            .addToBackStack(null)
            .commit()
    }
}