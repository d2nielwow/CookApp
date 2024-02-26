package com.daniel.cookmone.presentation.ui.choice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.daniel.cookmone.MAIN
import com.daniel.cookmone.R
import com.daniel.cookmone.domain.RecipeResponseItem


class ChoiceFragment : Fragment(R.layout.fragment_choice) {

    companion object {

        private const val MENU_ARG = "menu"

        fun newInstance(recipes: RecipeResponseItem): Fragment = ChoiceFragment().apply {
            arguments = Bundle().apply {
                putSerializable(MENU_ARG, recipes)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menu = requireArguments().getSerializable(MENU_ARG) as RecipeResponseItem

        val image = view.findViewById<ImageView>(R.id.imageChoice)
        val title = view.findViewById<TextView>(R.id.title)
        val energy = view.findViewById<TextView>(R.id.energy)
        val fat = view.findViewById<TextView>(R.id.fat)
        val protein = view.findViewById<TextView>(R.id.protein)

        title.text = menu.title
        energy.text = menu.energy.toString()
        fat.text = menu.fat.toString()
        protein.text = menu.protein.toString()

        Glide.with(requireContext())
            .load(menu.image_url)
            .into(image)
    }
}