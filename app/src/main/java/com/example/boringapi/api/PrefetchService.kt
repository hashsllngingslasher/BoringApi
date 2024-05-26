package com.example.boringapi.api

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object PrefetchService {
    private val client = OkHttpClient()

    fun prefetchUrl(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                throw Exception("Невозможно пройти по ссылке")
            }

            override fun onResponse(call: Call, response: Response) {
                response.close()
            }
        })
    }
}