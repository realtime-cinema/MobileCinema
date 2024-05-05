package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RANKING(
    val id:String? = null,
    val from_id:String? = null,
    val dest_id:String? = null,
    val ranking:Int? = null,
    val type:String? = null,
):Parcelable

data class RankingRespone(val RankingList:List<RANKING>)

