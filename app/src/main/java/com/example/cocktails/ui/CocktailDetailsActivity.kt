package com.example.cocktails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cocktails.R
import kotlinx.android.synthetic.main.activity_cocktail_details.*

class CocktailDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_details)
        see_id.text = intent.extras!!["drinkId"] as String
    }
}
