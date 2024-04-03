package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class USER(
    val id:Int,
    val name:String,
    val username:String,
    val role:Int,
):Parcelable

data class UserRespone(val UserList:List<USER>)

