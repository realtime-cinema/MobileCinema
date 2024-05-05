package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class USER(
    val id:String? = null,
    val name:String? = null,
    val username:String? = null,
    val role:Int? = null,
):Parcelable

data class UserRespone(val UserList:List<USER>)

