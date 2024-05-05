package com.example.momocinema.repository

import android.os.Parcelable
import android.text.BoringLayout
import kotlinx.parcelize.Parcelize


@Parcelize
data class USER(
    val id:String? = null,
    val firstName:String? = null,
    val lastName:String? = null,
    val email:String? = null,
    val password:String? = null,
    val role:String? = null,
    val authorities:List<AUTHOR>? =null,
    val accountNonExpired:Boolean? = null,
    val accountNonLocked:Boolean? = null,
    val credentialsNonExpired:Boolean? = null,
    val enabled:Boolean? = null,
    val username:String? = null,
):Parcelable

data class UserRespone(
    val `token`:String,
    val `user`:USER
)

