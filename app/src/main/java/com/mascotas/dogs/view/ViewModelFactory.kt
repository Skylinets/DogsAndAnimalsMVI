package com.example.mvianimalscompose.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mascotas.dogs.api.AnimalApi
import com.mascotas.dogs.api.AnimalRepo

class ViewModelFactory(private val api: AnimalApi): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(AnimalRepo(api)) as T
        }
        throw java.lang.IllegalArgumentException("Uknown class name")
    }



}