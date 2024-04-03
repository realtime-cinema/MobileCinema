package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SEAT_PRICE(
    val id:Int,
    val perform_id:Int,
    val x:Int,
    val y:Int,
    val price:Int,
):Parcelable

data class SeatPriceRespone(val SeatPriceList:List<SEAT_PRICE>)