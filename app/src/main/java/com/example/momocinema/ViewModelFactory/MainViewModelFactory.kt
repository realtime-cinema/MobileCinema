package com.example.momocinema.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.momocinema.ViewModel.FilmInfoViewModel
import com.example.momocinema.ViewModel.MainViewModel
import com.example.momocinema.ViewModel.SelectFilmViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel() as T
        }
        throw IllegalArgumentException("ViewModel class not found.")
    }
}