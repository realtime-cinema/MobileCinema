package com.example.momocinema.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.momocinema.ViewModel.SelectFilmViewModel
import com.example.momocinema.ViewModel.SelectPerformViewModel
import com.example.momocinema.ViewModel.SelectSeetViewModel

@Suppress("UNCHECKED_CAST")

class SelectSeetViewModelFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SelectSeetViewModel::class.java)){
            return SelectSeetViewModel() as T
        }
        throw IllegalArgumentException("ViewModel class not found.")
    }
}