package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class COMMENT(
    val id:Int,
    val user_id:Int,
    val dest_id:Int,
    val body:String,
):Parcelable

data class CommentRespone(val CommentList:List<COMMENT>)

