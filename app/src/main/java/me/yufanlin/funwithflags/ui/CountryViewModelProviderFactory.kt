package me.yufanlin.funwithflags.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yufanlin.funwithflags.domain.repository.CountryRepository
import me.yufanlin.funwithflags.ui.viewmodel.CountryViewModel

@Suppress("UNCHECKED_CAST")
class CountryViewModelProviderFactory(
    private val repository: CountryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel(repository) as T
    }
}