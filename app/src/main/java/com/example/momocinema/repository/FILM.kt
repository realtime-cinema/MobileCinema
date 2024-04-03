package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp


@Parcelize
data class FILM(
    val id:Int,
    val title:String,
    val director:String,
    val description:String,
    val picture_url:String,
    val trailer_url:String,
    val release_date:Timestamp,
    val language:String,
    val restrict_age:Int,
    val duration:Int,
):Parcelable

data class FilmRespone(val FilmList:List<FILM>)