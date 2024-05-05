package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CINEMA(
    val id:String? = null,
    val variant:String? = null,
    val name:String? = null,
):Parcelable

data class CinemaRespone(val CinemaList:List<CINEMA>)


