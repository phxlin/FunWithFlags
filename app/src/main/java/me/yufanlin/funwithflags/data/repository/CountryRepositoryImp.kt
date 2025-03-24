package me.yufanlin.funwithflags.data.repository

import me.yufanlin.funwithflags.data.remote.CountryApi
import me.yufanlin.funwithflags.domain.repository.CountryRepository

class CountryRepositoryImp(
    private val api: CountryApi
) : CountryRepository {
    override suspend fun getCountries() = api.getCountries()
}