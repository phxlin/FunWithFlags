package me.yufanlin.funwithflags.data.remote

import me.yufanlin.funwithflags.data.model.Country
import me.yufanlin.funwithflags.di.DaggerApiComponent
import retrofit2.Response
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountryApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    suspend fun getCountries(): Response<List<Country>> {
        return api.getCountries()
    }
}