package me.yufanlin.funwithflags.model

import me.yufanlin.funwithflags.dependencyInjection.DaggerApiComponent
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