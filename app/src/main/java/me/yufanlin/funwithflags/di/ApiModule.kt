package me.yufanlin.funwithflags.di

import dagger.Module
import dagger.Provides
import me.yufanlin.funwithflags.data.remote.CountriesService
import me.yufanlin.funwithflags.data.remote.CountryApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideCountriesApi(): CountryApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryApi::class.java)

    @Provides
    fun provideCountriesService(): CountriesService = CountriesService()

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com"
    }
}