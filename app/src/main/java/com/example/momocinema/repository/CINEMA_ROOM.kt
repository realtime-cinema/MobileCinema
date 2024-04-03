package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CINEMA_ROOM(
    val id:Int,
    val cinema_id:Int,
    val cinema_layout_id:Int,
    val type:Int,
    val name:String,
):Parcelable

data class CinemaRoomRespone(val CinemaRoomList:List<CINEMA_ROOM>)

