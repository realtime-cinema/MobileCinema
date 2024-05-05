package com.example.momocinema.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.momocinema.ViewModel.SelectFilmViewModel

@Suppress("UNCHECKED_CAST")

class SelectFilmViewModelFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SelectFilmViewModel::class.java)){
            return SelectFilmViewModel() as T
        }
        throw IllegalArgumentException("ViewModel class not found.")
    }
}