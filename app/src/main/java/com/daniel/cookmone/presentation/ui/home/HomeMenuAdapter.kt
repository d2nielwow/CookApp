package com.daniel.cookmone.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daniel.cookmone.MAIN
import com.daniel.cookmone.R
import com.daniel.cookmone.domain.RecipeResponseItem

class HomeMenuAdapter() : RecyclerView.Adapter<HomeMenuAdapter.MenuViewHolder>() {

    private val secondMenuList = ArrayList<RecipeResponseItem>()

    fun submitList(list: List<RecipeResponseItem>) {
        secondMenuList.clear()
        secondMenuList.addAll(list)
    }

    override fun onBindViewHolder(holder: HomeMenuAdapter.MenuViewHolder, position: Int) {
        val item = secondMenuList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = secondMenuList.size


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeMenuAdapter.MenuViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_rv_menu_second, parent, false)

        return MenuViewHolder(view)
    }

  inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.imageMenuSecond)
        private val title = view.findViewById<TextView>(R.id.titleMenuSecond)

        fun bind(recipe: RecipeResponseItem) {
            Glide.with(MAIN)
                .load(recipe.image_url)
                .into(image)

            title.text = recipe.title
        }
    }
}