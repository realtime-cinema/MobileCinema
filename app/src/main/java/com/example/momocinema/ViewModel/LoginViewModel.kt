package com.example.momocinema.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.APIService
import com.example.momocinema.APIService.SimpleApi
import com.example.momocinema.repository.FILM
import com.example.momocinema.repository.USER
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    fun login(navigateToAnotherScreen:(screenName:String, user:USER)->Unit,mainViewModel: MainViewModel,email:String, password:String){
        viewModelScope.launch {
            try {
                val retrofit = SimpleApi.retrofit.create(APIService::class.java)
                val loginRespone = retrofit.getUser(USER(email = email, password = password))
                Log.d("LOGIN SUCCESS", loginRespone.toString())
                if(loginRespone.body()!=null){
                    mainViewModel.updateAppState(true, loginRespone.body()!!.user)
                    Log.d("USER", mainViewModel.applicationState.value.user.toString())
                    navigateToAnotherScreen(ScreenName.UserScreen.route, loginRespone.body()!!.user)
                    mainViewModel.updateAppScreenState(2)
                } else{
//                    handle nhaapj laij thoong tin
                }
            }catch (e:Exception){
                Log.e("ERROR", e.message.toString())
            }
        }
    }
}