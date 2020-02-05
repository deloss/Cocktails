package com.example.cocktails.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

object APIClient {

    fun getClient() : APIInterface {

        val okHttpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build()

        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
            GsonConverterFactory.create()).build().create(APIInterface::class.java)
    }


}

