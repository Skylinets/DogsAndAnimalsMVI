package com.mascotas.dogs


import com.mascotas.dogs.data.model.Animal
import com.mascotas.dogs.data.model.Dog

sealed class MainState {
    object Loading: MainState()
    data class Animals(val animals: List<Animal>): MainState()
    data class Dogs(val dogs: List<Dog>): MainState()
    data class Error(val error: String?): MainState()
    data class LoadingProgress(val progress: Float) : MainState()
    object Idle : MainState()
}