package me.yufanlin.funwithflags.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.yufanlin.funwithflags.di.DaggerApiComponent
import me.yufanlin.funwithflags.data.remote.CountriesService
import me.yufanlin.funwithflags.data.model.Country
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var countriesService: CountriesService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

    private val _countryLoadError = MutableLiveData<String?>()
    val countryLoadError: LiveData<String?>
        get() = _countryLoadError

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        _loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = countriesService.getCountries()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _countries.postValue(response.body())
                    _countryLoadError.postValue(null)
                    _loading.postValue(false)
                } else onError("Error: ${response.message()}")
            }
        }
    }

    private fun onError(message: String) {
        _countryLoadError.postValue(message)
        _loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}