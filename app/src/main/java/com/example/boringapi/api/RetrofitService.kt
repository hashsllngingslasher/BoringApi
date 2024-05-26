package com.example.boringapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    //    private const val BASE_URL = "https://www.boredapi.com/api/"
    private const val BASE_URL = "https://bored-api.appbrewery.com/"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: BoringApi by lazy {
        retrofitBuilder.build().create(BoringApi::class.java)
    }
}