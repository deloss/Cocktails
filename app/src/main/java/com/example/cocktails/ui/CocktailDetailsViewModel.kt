package com.example.cocktails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cocktails.data.api.APIClient
import com.example.cocktails.data.repository.CocktailsRepository
import com.example.cocktails.data.vo.CocktailDetailsResponse
import io.reactivex.disposables.CompositeDisposable

class CocktailDetailsViewModel(private val id: String) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val repository = CocktailsRepository(APIClient.getClient(), compositeDisposable)

    val cocktailDetails : LiveData<CocktailDetailsResponse> by lazy {
        repository.fetchCocktailDetails(id)
    }

    val networkState = repository.networkState

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}