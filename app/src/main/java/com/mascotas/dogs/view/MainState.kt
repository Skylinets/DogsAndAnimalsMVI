package com.mascotas.dogs


import com.mascotas.dogs.model.Animal

sealed class MainState {

    object idle: MainState()
    object Loading: MainState()
    data class Animals(val animals: List<Animal>): MainState()
    data class Error(val error: String?): MainState()

}