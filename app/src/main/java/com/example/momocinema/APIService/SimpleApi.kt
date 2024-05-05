package com.example.momocinema.APIService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val okHttpClient = OkHttpClient()
    .newBuilder()
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4dWFuZ2lhcDA4MDZAZ21haWwuY29tIiwiaWF0IjoxNzE0Nzg1MjIzLCJleHAiOjE3MTU2NzQyNTV9.7dwbpPS0ERRG8E0zriAh_Td0peqQscrype2W2OzN-MI")
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