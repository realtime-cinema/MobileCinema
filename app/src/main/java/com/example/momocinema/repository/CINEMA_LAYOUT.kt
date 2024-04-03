package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CINEMA_LAYOUT(
    val id:Int,
    val max_x:Int,
    val max_y:Int,
):Parcelable

data class CinemaLayoutRespone(val CinemaLayoutList:List<CINEMA_LAYOUT>)

