package com.hyak4j.cb.tarvel.taipei.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenAPI {
    // 臺北旅遊網-旅遊資訊API
    private val apiUrl = "https://www.travel.taipei/open-api/"
    private val okHttpClient = OkHttpClient.Builder().build()
    val openAPIService by lazy {
        getRetrofit().create(OpenAPIService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}