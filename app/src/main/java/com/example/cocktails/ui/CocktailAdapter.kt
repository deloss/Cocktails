package com.example.cocktails.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktails.R
import com.example.cocktails.data.vo.Cocktail
import kotlinx.android.synthetic.main.cocktail_item.view.*

class CocktailAdapter(var cocktails: List<Cocktail>, val listener: CocktailRecyclerViewItemClickListener) :
    RecyclerView.Adapter<CocktailAdapter.CampgroundViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CampgroundViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.cocktail_item, parent, false)
    )

    override fun getItemCount() = cocktails.size
    override fun onBindViewHolder(holder: CampgroundViewHolder, position: Int) {
        holder.bind(cocktails[position], listener)
    }

    class CampgroundViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(cocktail: Cocktail, listener: CocktailRecyclerViewItemClickListener) {
            itemView.cocktail_title.text = cocktail.strDrink
            Glide.with(itemView.context).load(cocktail.strDrinkThumb).into(itemView.cocktail_image)
            itemView.cocktail_card.setOnClickListener{_ -> listener.onItemClicked(cocktail.idDrink)}
        }
    }
}
