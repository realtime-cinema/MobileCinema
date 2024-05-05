package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SEAT_PRICE(
    val id:String? = null,
    val perform_id:String? = null,
    val x:Int? = null,
    val y:Int? = null,
    val price:Int? = null,
):Parcelable

data class SeatPriceRespone(val SeatPriceList:List<SEAT_PRICE>)