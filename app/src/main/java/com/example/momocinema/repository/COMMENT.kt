package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class COMMENT(
    val id:String? = null,
    val user_id:String? = null,
    val dest_id:String? = null,
    val body:String? = null,
):Parcelable

data class CommentRespone(val CommentList:List<COMMENT>)

