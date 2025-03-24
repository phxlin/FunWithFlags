package me.yufanlin.funwithflags.data.remote

import me.yufanlin.funwithflags.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor().setLevel(
                                HttpLoggingInterceptor.Level.BODY
                            )
                        )
                        .build()
                )
                .build()
        }

        val api: CountryApi by lazy {
            retrofit.create(CountryApi::class.java)
        }
    }
}