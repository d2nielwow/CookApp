package com.daniel.cookmone.presentation.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daniel.cookmone.presentation.utils.MAIN
import com.daniel.cookmone.R
import com.daniel.cookmone.domain.RecipeResponseItem

class RecipeAdapter(
    private val listener: OnActionListener
    ) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private val menuList: ArrayList<RecipeResponseItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_menu, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = menuList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun submitList(list: List<RecipeResponseItem>){
        menuList.clear()
        menuList.addAll(list)

        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val image = view.findViewById<ImageView>(R.id.imageMenu)
        private val title = view.findViewById<TextView>(R.id.titleMenu)

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