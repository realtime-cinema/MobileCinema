package com.example.momocinema.ViewModel

import android.util.Log
import androidx.annotation.RestrictTo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.APIService
import com.example.momocinema.APIService.SimpleApi
import com.example.momocinema.repository.PICK_SEAT
import com.example.momocinema.repository.SEAT_PRICE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit
import retrofit2.create

class SelectSeetViewModel:ViewModel() {
    private val _selectSeatState:MutableState<SelectSeetState> = mutableStateOf(SelectSeetState())
    var selectSeetState = _selectSeatState
    suspend fun loadData(id:String){
        val retrofit = SimpleApi.retrofit.create(APIService::class.java)
        val listSeetRespone = retrofit.getSeatOfPerform(id)
        val listPickSeatRespone = retrofit.getPickSeatOfPerform(id)
        if (listSeetRespone.body() != null) {
            _selectSeatState.value = _selectSeatState.value.copy(
                listPickSeat = listPickSeatRespone.body()!!.data,
                data = listSeetRespone.body()!!.data
            )
        }
        Log.d("PICKSEAT", selectSeetState.value.listPickSeat.size.toString())
    }
    fun fetchSeet(id:String){
        GlobalScope.launch {
            try {
                loadData(id)
            }catch (e:Exception){
                Log.d("ERROR SEAT", e.toString())
                selectSeetState = mutableStateOf(SelectSeetState())
            }
        }
    }


    data class SelectSeetState(
        var data:List<SEAT_PRICE> = listOf(),
        val listPickSeat:List<PICK_SEAT>  = listOf()
    )
}