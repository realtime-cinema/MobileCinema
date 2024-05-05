package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PICK_SEAT(
    val id:String? = null,
    val user_id:String? = null,
    val perform_id:String? = null,
    val x:Int? = null,
    val y:Int? = null,
):Parcelable

data class PickSeatRespone(val PickSeatList:List<PICK_SEAT>)
