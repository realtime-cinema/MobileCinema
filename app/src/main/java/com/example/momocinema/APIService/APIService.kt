package com.example.momocinema.APIService

import com.example.momocinema.repository.CinemaLayoutRespone
import com.example.momocinema.repository.CinemaRespone
import com.example.momocinema.repository.CinemaRoomRespone
import com.example.momocinema.repository.CommentRespone
import com.example.momocinema.repository.FilmRespone
import com.example.momocinema.repository.PerformRespone
import com.example.momocinema.repository.PickSeatRespone
import com.example.momocinema.repository.RankingRespone
import com.example.momocinema.repository.SeatPriceRespone
import com.example.momocinema.repository.USER
import com.example.momocinema.repository.USERPOST
import com.example.momocinema.repository.UserRespone
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService{
    @GET("api/v1/films?") //listFilm
    suspend fun getListFilm():Response<FilmRespone>
    @GET("") //ranking *
    suspend fun getRanking():RankingRespone
    @GET("") //comment *
    suspend fun getComment():CommentRespone
    @POST("/api/auth/authenticate") //user
    suspend fun getUser(@Body user: USER):Response<UserRespone>
    @POST("/api/auth/register") //user
    suspend fun register(@Body user: USERPOST):Response<UserRespone>
    @GET("/api/v1/performs?") //perform
    suspend fun getAllPerform():Response<PerformRespone>
    @GET("/api/v1/seat-price/{idPerform}")
    suspend fun getSeatOfPerform(
        @Path("idPerform") idPerform : String,
    ):Response<SeatPriceRespone>
    @GET("/api/v1/pick-seat/{idPerform}")
    suspend fun getPickSeatOfPerform(
        @Path("idPerform") idPerform : String,
    ):Response<PickSeatRespone>
    @GET("/api/v1/rooms") //cinema room
    suspend fun getCinemaRoom():CinemaRoomRespone
    @GET("/api/v1/cinemas") //cinema
    suspend fun getAllCinema():CinemaRespone
    @GET("") //cinema layout
    suspend fun getCinemaLayout():CinemaLayoutRespone
}