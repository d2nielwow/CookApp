package com.daniel.cookmone.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.daniel.cookmone.R
import com.daniel.cookmone.domain.RecipeResponseItem
import com.daniel.cookmone.presentation.ui.choice.ChoiceFragment

class HomeFragment : Fragment(), HomeAdapter.OnActionListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private lateinit var recycler: RecyclerView

    private val adapter = HomeAdapter(this)

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.rv_menu_second)
        recycler.adapter = adapter

        viewModel.recipeListLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onItemClicked(menu: RecipeResponseItem) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ChoiceFragment.newInstance(menu))
            .addToBackStack(null)
            .commit()
    }
}