package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CINEMA_MANAGER(
    val id:String? = null,
    val cinema_id:String? = null,
    val user_id:String? = null,
):Parcelable

data class CinemaManagerRespone(val CinemaManagerList:List<CINEMA_MANAGER>)


