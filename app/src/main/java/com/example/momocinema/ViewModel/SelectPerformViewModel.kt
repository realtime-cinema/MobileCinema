package com.example.momocinema.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.recipeService
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.CINEMA
import com.example.momocinema.repository.CINEMA_ROOM
import com.example.momocinema.repository.PERFORM
import kotlinx.coroutines.launch

class SelectPerformViewModel:ViewModel() {

//    Begin fake data---------------------------------------------------
    private val _listPerformSelectFake = mutableStateOf(PerformSelectState())
    val listPerformSelectStateFake = _listPerformSelectFake
    fun fakeFetchListPerform(){
        _listPerformSelectFake.value = _listPerformSelectFake.value.copy(
            loading = false,
            error = false,
            listPerform = DatasourceCloneAPIData().loadPerforms(),
            listCinemaRoom = DatasourceCloneAPIData().loadCinemaRooms(),
            listCinema = DatasourceCloneAPIData().loadCinemas()
        )
    }
//    End fake data---------------------------------------------------
    private val _listPerformSelect = mutableStateOf(PerformSelectState())
    val listPerformSelectState = _listPerformSelect
    fun fetchListPerform(){
        viewModelScope.launch {
            try {
                val fetchListPerform = recipeService.getPerform()
                val fetchListCinemaRoom = recipeService.getCinemaRoom()
                val fetchListCinema = recipeService.getCinema()
                _listPerformSelect.value = _listPerformSelect.value.copy(
                    loading = false,
                    error =false,
                    listPerform = fetchListPerform.PerformList,
                    listCinemaRoom = fetchListCinemaRoom.CinemaRoomList,
                    listCinema = fetchListCinema.CinemaList,
                )
            }catch (e:Exception){
                _listPerformSelect.value = _listPerformSelect.value.copy(
                    loading = false,
                    error = true,
                )
            }
        }
    }
    init {
        fakeFetchListPerform()
        /*fetchListPerform()*/
    }



    data class PerformSelectState(
        val loading:Boolean = false,
        val error:Boolean = false,
        val listPerform:List<PERFORM> = listOf(),
        val listCinemaRoom:List<CINEMA_ROOM> = listOf(),
        val listCinema:List<CINEMA> = listOf(),
    )
}