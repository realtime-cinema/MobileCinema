package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp


@Parcelize
data class PAYMENT(
    val id:String? = null,
    val user_id:String? = null,
    val cinema_id:String? = null,
    val date_create:Timestamp? = null,
    val amount:Int? = null,
):Parcelable

data class PaymentRespone(val PaymentList:List<PAYMENT>)