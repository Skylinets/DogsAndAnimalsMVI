package com.mascotas.dogs.api


import com.mascotas.dogs.model.Animal
import retrofit2.http.GET

interface AnimalApi {
    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>

}