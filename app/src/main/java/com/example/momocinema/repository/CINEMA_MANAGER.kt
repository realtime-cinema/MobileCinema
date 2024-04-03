package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CINEMA_MANAGER(
    val id:Int,
    val cinema_id:Int,
    val user_id:Int,
):Parcelable

data class CinemaManagerRespone(val CinemaManagerList:List<CINEMA_MANAGER>)


