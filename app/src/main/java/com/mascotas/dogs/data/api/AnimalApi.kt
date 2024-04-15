package com.mascotas.dogs.data.api


import com.mascotas.dogs.data.model.Animal
import retrofit2.http.GET

interface AnimalApi {
    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>

}