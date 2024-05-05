package com.example.momocinema.APIService

import com.example.momocinema.repository.CinemaLayoutRespone
import com.example.momocinema.repository.CinemaRespone
import com.example.momocinema.repository.CinemaRoomRespone
import com.example.momocinema.repository.CommentRespone
import com.example.momocinema.repository.FilmRespone
import com.example.momocinema.repository.PerformRespone
import com.example.momocinema.repository.RankingRespone
import com.example.momocinema.repository.UserRespone
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService{
    @GET("api/v1/films?") //listFilm
    suspend fun getListFilm():Response<FilmRespone>
    @GET("") //ranking *
    suspend fun getRanking():RankingRespone
    @GET("") //comment *
    suspend fun getComment():CommentRespone
    @GET("") //user
    suspend fun getUser():UserRespone
    @GET("/api/v1/performs?") //perform
    suspend fun getAllPerform():Response<PerformRespone>
    @GET("/api/v1/rooms") //cinema room
    suspend fun getCinemaRoom():CinemaRoomRespone
    @GET("/api/v1/cinemas") //cinema
    suspend fun getAllCinema():CinemaRespone
    @GET("") //cinema layout
    suspend fun getCinemaLayout():CinemaLayoutRespone
}