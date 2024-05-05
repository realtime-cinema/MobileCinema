package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CINEMA_LAYOUT(
    val id:String? = null,
    val x_index:Int? = null,
    val y_index:Int? = null,
):Parcelable

data class CinemaLayoutRespone(val CinemaLayoutList:List<CINEMA_LAYOUT>)

