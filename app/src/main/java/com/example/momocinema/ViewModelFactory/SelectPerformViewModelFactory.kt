package com.example.momocinema.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.ViewModel.SelectPerformViewModel

@Suppress("UNCHECKED_CAST")

class SelectPerformViewModelFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SelectPerformViewModel::class.java)){
            return SelectPerformViewModel() as T
        }
        throw IllegalArgumentException("ViewModel class not found.")
    }
}