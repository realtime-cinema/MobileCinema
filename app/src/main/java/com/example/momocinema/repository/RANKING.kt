package com.example.momocinema.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RANKING(
    val id:Int,
    val dest_id:Int,
    val ranking:Int,
):Parcelable

data class RankingRespone(val RankingList:List<RANKING>)

