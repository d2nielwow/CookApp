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

class HomeAdapter(private val listener: OnActionListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val menuList = ArrayList<RecipeResponseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_menu_second, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
       val item = menuList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = menuList.size

    fun submitList(list: List<RecipeResponseItem>){
        menuList.clear()
        menuList.addAll(list)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val image = view.findViewById<ImageView>(R.id.imageMenuSecond)
        private val title = view.findViewById<TextView>(R.id.titleMenuSecond)

        fun bind(recipe: RecipeResponseItem){
            Glide.with(MAIN)
                .load(recipe.image_url)
                .into(image)

            title.text = recipe.title

            itemView.setOnClickListener {
                listener.onItemClicked(recipe)
            }
        }
    }

    interface OnActionListener{
        fun onItemClicked(menu: RecipeResponseItem)
    }

}