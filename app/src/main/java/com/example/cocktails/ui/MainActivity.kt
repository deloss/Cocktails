package com.example.cocktails.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktails.R
import com.example.cocktails.data.repository.NetworkState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CocktailRecyclerViewItemClickListener {

    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel()

        val cocktailAdapter = CocktailAdapter(ArrayList(), this)
        cocktailsRecyclerView.layoutManager = LinearLayoutManager(this)
        cocktailsRecyclerView.setHasFixedSize(false)
        cocktailsRecyclerView.adapter = cocktailAdapter

        viewModel.cocktailsList.observe(this, Observer {
            cocktailAdapter.cocktails = it.drinks
            cocktailAdapter.notifyDataSetChanged()
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if(it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if(it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })

    }

    private fun getViewModel() : MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainActivityViewModel() as T
            }

        })[MainActivityViewModel::class.java]
    }

    override fun onItemClicked(cocktailId: String) {
        val intent = Intent(this, CocktailDetailsActivity::class.java)
        intent.putExtra("drinkId", cocktailId)
        startActivity(intent)
    }
}
