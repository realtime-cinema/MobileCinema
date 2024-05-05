package com.example.momocinema.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momocinema.APIService.APIService
import com.example.momocinema.APIService.SimpleApi
import com.example.momocinema.repository.USER
import com.example.momocinema.repository.USERPOST
import kotlinx.coroutines.launch

class RegisterViewModel:ViewModel() {
    fun register(mainViewModel:MainViewModel, firstName:String, lastName:String, email:String, password:String, navigateToAnotherScreen:(screenName:String, user:USER)->Unit){
        viewModelScope.launch {
            try {
                val retrofit = SimpleApi.retrofit.create(APIService::class.java)
                Log.d(firstName, lastName)
                val registerResponce = retrofit.register(USERPOST(first_name = firstName, last_name = lastName, email = email, password = password))
                Log.d("REGISTER SUCCESS", registerResponce.body().toString())
                if(registerResponce.body()!=null){
                    mainViewModel.updateAppState(true, registerResponce.body()!!.user)
                    Log.d("USER", mainViewModel.applicationState.value.user.toString())
                    navigateToAnotherScreen(ScreenName.UserScreen.route, registerResponce.body()!!.user)
                    mainViewModel.updateAppScreenState(2)
                } else{
//                    handle nhaapj laij thoong tin
                }
            }catch (e:Exception){

            }
        }
    }
}