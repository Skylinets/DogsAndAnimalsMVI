package com.mascotas.dogs.api

class AnimalRepo(private val api: AnimalApi) {
    suspend fun getAnimals() = api.getAnimals()

    suspend fun searchAnimal() = api.getAnimals().listIterator()

}

