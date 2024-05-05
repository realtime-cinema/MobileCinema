package com.example.momocinema.APIService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val okHttpClient = OkHttpClient()
    .newBuilder()
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YWluZ3V5ZW5AZ21haWwuY29tIiwiaWF0IjoxNzE0ODk2Njg1LCJleHAiOjE3MTU3ODU3MTh9.8fmCADR1Wb3kOhBHS4N1zZvgTGd5GqAg_FNYB3n8Lf4")
            .build()
        chain.proceed(request)
    }
    .build()

object SimpleApi {
    val retrofit = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl("http://10.0.2.2:9999/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}