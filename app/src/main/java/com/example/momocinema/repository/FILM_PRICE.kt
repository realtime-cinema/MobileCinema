package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FILM_PRICE(
    val id:String? = null,
    val film_id:String? = null,
    val type:String? = null,
    val price:Int? = null,
):Parcelable

data class FilmPriceRespone(val FilmPriceList:List<FILM_PRICE>)

