package com.example.momocinema.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.recipeService
import com.example.momocinema.data.DatasourceCloneAPIData
import com.example.momocinema.repository.COMMENT
import com.example.momocinema.repository.USER
import kotlinx.coroutines.launch

class FilmInfoViewModel:ViewModel() {

//    Begin fake data---------------------------------------------------------
    private val _listFilmInfoStateFake = mutableStateOf<FilmInfoState>(FilmInfoState())
    val listFilmInfoStateFake = _listFilmInfoStateFake
    fun fakeFetchFilmInfo(){
        _listFilmInfoStateFake.value = _listFilmInfoStateFake.value.copy(
            loading = false,
            error = false,
            listComment = DatasourceCloneAPIData().loadComments(),
            listUser = DatasourceCloneAPIData().loadUser(),
        )
    }
//    End fake data-----------------------------------------------------------

    private val _listFilmInfoState = mutableStateOf(FilmInfoState())
    val listFilmInfoState = _listFilmInfoState
    init{
        /*fetchFilmInfo()*/
        fakeFetchFilmInfo()

    }

    fun fetchFilmInfo(){  // only more readable, this usage is load list comment and list user
        viewModelScope.launch {
            try {
                val listComment = recipeService.getComment()
                val listUser = recipeService.getUser()
                _listFilmInfoState.value = _listFilmInfoState.value.copy(
                    loading = false,
                    error= false,
                    listComment = listComment.CommentList,
                    listUser = listUser.UserList,
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