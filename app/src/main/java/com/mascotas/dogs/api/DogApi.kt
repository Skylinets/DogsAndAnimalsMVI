package com.mascotas.dogs.api

import com.mascotas.dogs.model.Animal
import com.mascotas.dogs.model.Dog
import com.mascotas.dogs.view.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogApi {

    @GET
    suspend fun getDogsByBreeds(@Url url:String): Response<DogsResponse>



    @GET("/Images")
    suspend fun getDogs(): List<Dog>
}