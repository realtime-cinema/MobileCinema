package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Time
import java.sql.Timestamp

@Parcelize
data class PERFORM(
    val id:String? = null,
    val film:FILM? = null,
    val view_type:VIEW_TYPE? = null,
    val translate_type:TRANSLATE_TYPE? = null,
    val cinema_room:CINEMA_ROOM? = null,
    val start_time:String? = null,
    val end_time:String? = null,
):Parcelable




data class PerformRespone(
    val `data`: List<PERFORM>,
    val message: String
)