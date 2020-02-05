package com.example.cocktails.data.vo


import com.google.gson.annotations.SerializedName

data class CocktailDetails(
    val strDrink: String,
    val strDrinkThumb: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String
)