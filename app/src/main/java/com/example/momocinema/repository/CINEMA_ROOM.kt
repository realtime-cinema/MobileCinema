package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CINEMA_ROOM(
    val id:String? = null,
    val name:String? = null,
    val cinema:CINEMA? = null,
    val cinema_layout:CINEMA_LAYOUT? = null,
):Parcelable

data class CinemaRoomRespone(val CinemaRoomList:List<CINEMA_ROOM>)

