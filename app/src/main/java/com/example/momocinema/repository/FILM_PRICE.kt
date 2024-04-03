package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FILM_PRICE(
    val id:Int,
    val film_id:Int,
    val type:String,
    val price:Int,
):Parcelable

data class FilmPriceRespone(val FilmPriceList:List<FILM_PRICE>)

