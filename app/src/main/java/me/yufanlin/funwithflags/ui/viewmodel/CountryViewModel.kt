package me.yufanlin.funwithflags.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yufanlin.funwithflags.data.model.Country
import me.yufanlin.funwithflags.domain.repository.CountryRepository
import me.yufanlin.funwithflags.util.Resource
import retrofit2.Response

class CountryViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _countries: MutableLiveData<Resource<List<Country>>> = MutableLiveData()
    val countries: LiveData<Resource<List<Country>>>
        get() = _countries

    var countriesList: List<Country>? = null

    init {
        getCountries()
    }

    fun getCountries() = viewModelScope.launch {
        _countries.postValue(Resource.Loading())
        val response = countryRepository.getCountries()
        _countries.postValue(handleCountriesResponse(response))
    }

    private fun handleCountriesResponse(response: Response<List<Country>>): Resource<List<Country>> {
        if (response.isSuccessful)
            response.body()?.let { resultResponse ->
                if (countriesList == null)
                    countriesList = resultResponse
                else
                    countriesList?.toMutableList()?.addAll(resultResponse)

                return Resource.Success(countriesList ?: resultResponse)
            }
        return Resource.Error(response.message())
    }
}