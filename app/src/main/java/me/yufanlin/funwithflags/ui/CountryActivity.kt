package me.yufanlin.funwithflags.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.yufanlin.funwithflags.data.remote.RetrofitInstance
import me.yufanlin.funwithflags.data.repository.CountryRepositoryImp
import me.yufanlin.funwithflags.databinding.ActivityCountryBinding
import me.yufanlin.funwithflags.ui.adapter.CountriesAdapter
import me.yufanlin.funwithflags.ui.viewmodel.CountryViewModel
import me.yufanlin.funwithflags.util.Resource

class CountryActivity : AppCompatActivity() {

    private val tag = CountryActivity::class.java.simpleName

    private lateinit var viewModel: CountryViewModel
    private lateinit var countriesAdapter: CountriesAdapter
    private lateinit var binding: ActivityCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            CountryViewModelProviderFactory(
                CountryRepositoryImp(
                    RetrofitInstance.api
                )
            )
        )[CountryViewModel::class.java]

        setRecyclerView()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { data ->
                        countriesAdapter.diff.submitList(data.toList())
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(tag, "An error occurred: $message")
                    }
                }

                is Resource.Loading -> showProgressBar()
            }
        }
    }

    private fun setRecyclerView() {
        countriesAdapter = CountriesAdapter()
        binding.rvCountries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.getCountries()
        }
    }

    private fun showProgressBar() {
        binding.loadingCountryProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.loadingCountryProgressBar.visibility = View.INVISIBLE
    }
}