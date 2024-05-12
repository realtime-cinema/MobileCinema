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
import com.example.momocinema.repository.SEAT_CHANGE
import com.example.momocinema.repository.SEAT_PRICE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit
import retrofit2.create

class SelectSeetViewModel:ViewModel() {
    private val _selectSeatState:MutableState<SelectSeetState> = mutableStateOf(SelectSeetState())
    var selectSeetState = _selectSeatState
    val retrofit = SimpleApi.retrofit.create(APIService::class.java)
    suspend fun loadData(id:String){
        _selectSeatState.value = _selectSeatState.value.copy(
            loading = true
        )
        val listSeetRespone = retrofit.getSeatOfPerform(id)
        val listPickSeatRespone = retrofit.getPickSeatOfPerform(id)
        if (listSeetRespone.body() != null) {
            _selectSeatState.value = _selectSeatState.value.copy(
                listPickSeat = listPickSeatRespone.body()!!.data,
                data = listSeetRespone.body()!!.data,
                loading = false,
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
    fun postSeat(idPerform:String, seat:PICK_SEAT){
        GlobalScope.launch {
            try {
                var postSeatRespone = retrofit.postSeatOfPerform(idPerform , listOf(seat))
                Log.d("POST_SEAT", postSeatRespone.toString())

            }catch (e:Exception){
                Log.d("POST_SEAT_ERR", e.toString())
            }
        }
    }
    fun deleteSeat(idPerform:String, seat:PICK_SEAT){
        GlobalScope.launch {
            try {
                var deleteSeatRespone = retrofit.deleteSeatOfPerform(idPerform ,seat)
                Log.d("DELETE_SEAT", "SUCCESS")
            }catch (e:Exception){
                Log.d("POST_SEAT_ERR", e.toString())
            }
        }
    }


    data class SelectSeetState(
        var data:List<SEAT_PRICE> = listOf(),
        val listPickSeat:List<PICK_SEAT>  = listOf(),
        val loading:Boolean = false,
    )
}