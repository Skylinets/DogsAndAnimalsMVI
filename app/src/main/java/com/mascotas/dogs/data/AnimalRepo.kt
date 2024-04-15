package com.mascotas.dogs.data

import com.mascotas.dogs.data.api.AnimalApi

class AnimalRepo(private val api: AnimalApi) {
    suspend fun getAnimals() = api.getAnimals()

}

