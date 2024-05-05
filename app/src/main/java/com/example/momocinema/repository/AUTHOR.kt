package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AUTHOR(
    val authority:String? = null,
):Parcelable

