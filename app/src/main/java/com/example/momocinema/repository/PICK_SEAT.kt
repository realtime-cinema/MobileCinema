package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PICK_SEAT(
    val id:Int,
    val user_id:Int,
    val perform_id:Int,
    val x:Int,
    val y:Int,
):Parcelable

data class PickSeatRespone(val PickSeatList:List<PICK_SEAT>)
