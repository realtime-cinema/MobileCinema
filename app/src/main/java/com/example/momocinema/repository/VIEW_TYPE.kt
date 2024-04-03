package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class VIEW_TYPE(
    val id:Int,
    val view_type:String,
):Parcelable

data class ViewTypeRespone(val ViewTypeList:List<VIEW_TYPE>)
