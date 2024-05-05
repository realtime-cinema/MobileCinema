package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp


@Parcelize
data class FILM(
    val country: String? =null,
    val description: String? =null,
    val director: String? =null,
    val duration: Int? =null,
    val id: String? =null,
    val picture_url: String? =null,
    val release_date: String? =null,
    val restrict_age: Int? =null,
    val tags: List<TAG>? =null,
    val title: String? =null,
    val trailer_url: String? =null
):Parcelable

data class FilmRespone(
    val `data`: List<FILM>,
    val message: String
)