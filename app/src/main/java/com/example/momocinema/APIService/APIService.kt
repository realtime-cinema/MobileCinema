package com.example.momocinema.APIService

import com.example.momocinema.repository.CommentRespone
import com.example.momocinema.repository.FilmRespone
import com.example.momocinema.repository.RankingRespone
import com.example.momocinema.repository.TagRespone
import com.example.momocinema.repository.UserRespone
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private var url = "" //add later
private var retrofit =Retrofit.Builder().baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var recipeService = retrofit.create(APIService::class.java)

interface APIService{
    @GET("") //listFilm
    suspend fun getListFilm():FilmRespone
    @GET("") //ranking
    suspend fun getRanking():RankingRespone
    @GET("") //ranking
    suspend fun getTag():TagRespone
    @GET("") //comment
    suspend fun getComment():CommentRespone
    @GET("") //user
    suspend fun getUser():UserRespone
}