package com.mascotas.dogs.data.api

import com.mascotas.dogs.data.response.DogsImageResponse
import com.mascotas.dogs.data.response.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breeds/list")
    suspend fun getBreedsList(): Response<DogsResponse>

    @GET("breed/{breed}/images/random")
    suspend fun getBreedRandomImage(@Path("breed") breed: String): Response<DogsImageResponse>
}

