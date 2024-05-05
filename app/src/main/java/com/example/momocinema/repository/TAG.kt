package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TAG(
    val id: String? = null,
    val name: String? = null
):Parcelable



