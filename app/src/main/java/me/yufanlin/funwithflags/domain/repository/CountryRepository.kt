package me.yufanlin.funwithflags.domain.repository

import me.yufanlin.funwithflags.data.model.Country
import retrofit2.Response

interface CountryRepository {

    suspend fun getCountries(): Response<List<Country>>
}