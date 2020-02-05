package com.example.cocktails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cocktails.data.api.APIClient
import com.example.cocktails.data.repository.CocktailsRepository
import com.example.cocktails.data.vo.CocktailResponse
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val repository = CocktailsRepository(APIClient.getClient(), compositeDisposable)

    val cocktailsList : LiveData<CocktailResponse> by lazy {
        repository.fetchCocktails()
    }

    val networkState = repository.networkState

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}