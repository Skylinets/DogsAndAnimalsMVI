package com.mascotas.dogs


import com.mascotas.dogs.model.Animal
import com.mascotas.dogs.model.Dog
import com.mascotas.dogs.view.DogsResponse
import retrofit2.Response

sealed class MainState {

    object idle: MainState()
    object Loading: MainState()
    data class Animals(val animals: List<Animal>): MainState()
    data class Dogs(val dogs: List<Dog>): MainState()
    data class Error(val error: String?): MainState()

}