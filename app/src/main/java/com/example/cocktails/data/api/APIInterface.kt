package com.example.cocktails.data.api

import com.example.cocktails.data.vo.CocktailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("filter.php?g=Cocktail_glass")
    fun getCocktails() : Single<CocktailResponse>


}