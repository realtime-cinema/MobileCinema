package com.example.momocinema.ViewModel

sealed class ScreenName(val route:String){
    object SelectFilmScreen:ScreenName("select_film")
    object FilmInfoScreen:ScreenName("film_info")
    object SelectPerformScreen:ScreenName("select_perform")
    object SelectSeatScreen:ScreenName("select_seat")
    object LoginScreen:ScreenName("login")
    object RegisterScreen:ScreenName("register")
    object DetailReviewScreen:ScreenName("detail_review")
    object UserScreen:ScreenName("user")
    object SelectFilmAndPerformScreen:ScreenName("film_and_perform")
}