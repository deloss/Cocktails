package com.example.cocktails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.cocktails.R
import com.example.cocktails.data.repository.NetworkState
import com.example.cocktails.data.vo.CocktailDetails
import kotlinx.android.synthetic.main.activity_cocktail_details.*
import kotlinx.android.synthetic.main.cocktail_item.view.*

class CocktailDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel : CocktailDetailsViewModel
    private lateinit var drinkId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_details)
        drinkId = intent.extras!!["drinkId"] as String
        viewModel = getViewModel()

        viewModel.cocktailDetails.observe(this, Observer {
            loadCocktail(it.drinks[0])
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if(it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if(it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })

    }

    fun loadCocktail(cocktail: CocktailDetails) {
        cocktail_name.text = cocktail.strDrink
        Glide.with(this).load(cocktail.strDrinkThumb).into(cocktail_image)
        if(cocktail.strIngredient1 != null) {
            addIngredient(cocktail.strIngredient1)
        }
        if(cocktail.strIngredient2 != null) {
            addIngredient(cocktail.strIngredient2)
        }
        if(cocktail.strIngredient3 != null) {
            addIngredient(cocktail.strIngredient3)
        }
        if(cocktail.strIngredient4 != null) {
            addIngredient(cocktail.strIngredient4)
        }
    }

    fun addIngredient(ingredient: String) {
        val textView = TextView(this)
        textView.text = ingredient
        ingredients_section.addView(textView)
    }

    private fun getViewModel() : CocktailDetailsViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CocktailDetailsViewModel(drinkId) as T
            }

        })[CocktailDetailsViewModel::class.java]
    }
}
