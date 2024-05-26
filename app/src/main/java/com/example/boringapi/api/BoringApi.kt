package com.example.boringapi.api

import com.example.boringapi.model.Leisure
import retrofit2.Call
import retrofit2.http.GET

interface BoringApi {
    @GET("random")
    fun getActivity(
    ) : Call<Leisure>
}