package com.example.mvianimalscompose.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mascotas.dogs.data.AnimalRepo
import com.mascotas.dogs.data.DogsRepo
import com.mascotas.dogs.data.api.AnimalApi
import com.mascotas.dogs.data.api.DogApi

class ViewModelFactory(
    private val api: AnimalApi,
    private val dogApi: DogApi
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(AnimalRepo(api), DogsRepo(dogApi)) as T
        }
        throw java.lang.IllegalArgumentException("Uknown class name")
    }
}