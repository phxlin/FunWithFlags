package me.yufanlin.funwithflags.dependencyInjection

import dagger.Module
import dagger.Provides
import me.yufanlin.funwithflags.model.CountriesService
import me.yufanlin.funwithflags.model.CountryApi
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