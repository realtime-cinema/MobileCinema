package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp


@Parcelize
data class PAYMENT(
    val id:Int,
    val user_id:Int,
    val cinema_id:Int,
    val date_create:Timestamp,
    val amount:Int,
):Parcelable

data class PaymentRespone(val PaymentList:List<PAYMENT>)