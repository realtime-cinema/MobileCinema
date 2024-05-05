package com.example.momocinema.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.momocinema.ViewModel.FilmInfoViewModel
import com.example.momocinema.ViewModel.SelectFilmViewModel

@Suppress("UNCHECKED_CAST")
class FilmInfoViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FilmInfoViewModel::class.java)){
            return FilmInfoViewModel() as T
        }
        throw IllegalArgumentException("ViewModel class not found.")
    }
}