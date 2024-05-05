package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PICK_SEAT(
    val id:String? = null,
    val userDTO:USER? = null,
    val performDTO:PERFORM? = null,
    val x:Int? = null,
    val y:Int? = null,
):Parcelable

data class PickSeatRespone(
    val message:String,
    val data:List<PICK_SEAT>
)
