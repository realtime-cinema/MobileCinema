package com.example.momocinema.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.momocinema.ViewModel.FilmInfoViewModel
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.RegisterViewModel
import com.example.momocinema.ViewModel.SelectFilmViewModel

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel() as T
        }
        throw IllegalArgumentException("ViewModel class not found.")
    }
}