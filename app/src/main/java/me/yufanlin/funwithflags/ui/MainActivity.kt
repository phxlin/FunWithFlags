package me.yufanlin.funwithflags.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import me.yufanlin.funwithflags.databinding.ActivityMainBinding
import me.yufanlin.funwithflags.ui.adapter.CountryListAdapter
import me.yufanlin.funwithflags.ui.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java]
        viewModel.refresh()

        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this) { countries ->
            countries?.let {
                binding.countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        }

        viewModel.countryLoadError.observe(this) { isError ->
            isError?.let {
                binding.listError.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.countriesList.visibility = View.GONE
                }
            }
        }
    }
}