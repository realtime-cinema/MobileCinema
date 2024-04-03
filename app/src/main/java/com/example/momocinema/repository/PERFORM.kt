package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Time
import java.sql.Timestamp

@Parcelize
data class PERFORM(
    val id:Int,
    val film_id:Int,
    val view_type_id:Int,
    val translate_type_id:Int,
    val dest_id:Int,
    val price:Int,
    val start_time:Timestamp,
    val end_time:Timestamp,
):Parcelable

data class PerformRespone(val PerformList:List<PERFORM>)