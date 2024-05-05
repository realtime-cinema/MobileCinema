package com.example.momocinema.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.APIService
import com.example.momocinema.APIService.SimpleApi
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.CINEMA
import com.example.momocinema.repository.CINEMA_ROOM
import com.example.momocinema.repository.PERFORM
import kotlinx.coroutines.launch
import retrofit2.create

class SelectPerformViewModel:ViewModel() {


    private val _listPerformSelect = mutableStateOf(PerformSelectState())
    val listPerformSelectState = _listPerformSelect
    val simpleApi = SimpleApi.retrofit.create(APIService::class.java)
    fun fetchListPerform(){
        viewModelScope.launch {
            try {
                val fetchListPerform = simpleApi.getAllPerform()
                Log.d("SUCCESS", fetchListPerform.body().toString())
                fetchListPerform.body()?.let {
                    _listPerformSelect.value = _listPerformSelect.value.copy(
                        loading = false,
                        error =false,
                        listPerform = fetchListPerform.body()!!.data,
                    )
                }

            }catch (e:Exception){
                _listPerformSelect.value = _listPerformSelect.value.copy(
                    loading = false,
                    error = true,
                )
            }
        }
    }



    data class PerformSelectState(
        val loading:Boolean = false,
        val error:Boolean = false,
        val listPerform:List<PERFORM> = listOf(),
    )
}