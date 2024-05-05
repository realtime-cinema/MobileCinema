package com.example.momocinema.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.APIService
import com.example.momocinema.APIService.SimpleApi
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.COMMENT
import com.example.momocinema.repository.USER
import kotlinx.coroutines.launch
import retrofit2.create

class FilmInfoViewModel:ViewModel() {



    private val _listFilmInfoState = mutableStateOf(FilmInfoState())
    val listFilmInfoState = _listFilmInfoState
    private val simpleApi = SimpleApi.retrofit.create(APIService::class.java)
    fun fetchFilmInfo(){  // only more readable, this usage is load list comment and list user
        viewModelScope.launch {
            try {
                val listComment = DatasourceCloneAPIData().loadComments()
                val listUser = DatasourceCloneAPIData().loadUser()  //TODO:Tạm thời cứ như thế này, fetch data trong phần navigation k hiểu sao nó fetch nhiều lần khi navigate tới, khi nào có data trong db thì sẽ suy nghĩ fetch ở nơi khác
                Log.d("SUCCESS", listComment.toString())
                _listFilmInfoState.value = _listFilmInfoState.value.copy(
                    loading = false,
                    error= false,
                    listComment = listComment,
                    listUser = listUser,
                )
            }catch (e:Exception){
                _listFilmInfoState.value = _listFilmInfoState.value.copy(
                    loading = false,
                    error = true,
                )
            }
        }
    }

    data class FilmInfoState(
        val loading:Boolean = false,
        val error:Boolean = false,
        val listComment:List<COMMENT> = listOf(),
        val listUser:List<USER> = listOf(),
    )
}