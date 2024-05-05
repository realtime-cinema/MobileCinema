package com.example.momocinema.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.momocinema.repository.USER

class MainViewModel:ViewModel() {
    private var _applicationState = mutableStateOf(ApplicationState())
    val applicationState = _applicationState
    fun updateAppState(isAuthor:Boolean, user:USER){
        _applicationState.value = _applicationState.value.copy(
            isAuthor = isAuthor,
            user = user
        )
    }
    fun updateAppScreenState(screenNum:Int){
        _applicationState.value = _applicationState.value.copy(
            indexScreen = screenNum
        )
    }
    data class ApplicationState(
        val isAuthor:Boolean = false,
        val user:USER? = null,
        val indexScreen:Int=0,
    )
}