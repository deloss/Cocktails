package com.example.cocktails.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocktails.data.api.APIInterface
import com.example.cocktails.data.vo.CocktailDetailsResponse
import com.example.cocktails.data.vo.CocktailResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CocktailsRepository(private val apiClient: APIInterface, private val compositeDisposable: CompositeDisposable) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState : LiveData<NetworkState>
        get() = _networkState

    fun fetchCocktails() : LiveData<CocktailResponse> {
        val cocktailsList = MutableLiveData<CocktailResponse>()
        _networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(apiClient.getCocktails().subscribeOn(Schedulers.io()).subscribe(
            {
                cocktailsList.postValue(it)
                _networkState.postValue(NetworkState.LOADED)
            },
            {
                _networkState.postValue(NetworkState.ERROR)
                Log.e("RecipesRepository", it.message)
            }
        ))
        return cocktailsList
    }

    fun fetchCocktailDetails(id: String) : LiveData<CocktailDetailsResponse> {
        val cocktailsList = MutableLiveData<CocktailDetailsResponse>()
        _networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(apiClient.getCocktailDetails(id).subscribeOn(Schedulers.io()).subscribe(
            {
                cocktailsList.postValue(it)
                _networkState.postValue(NetworkState.LOADED)
            },
            {
                _networkState.postValue(NetworkState.ERROR)
                Log.e("RecipesRepository", it.message)
            }
        ))
        return cocktailsList
    }

}