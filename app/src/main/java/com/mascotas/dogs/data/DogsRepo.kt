package com.mascotas.dogs.data

import com.mascotas.dogs.data.api.DogApi
import com.mascotas.dogs.data.response.DogsImageResponse
import com.mascotas.dogs.data.response.DogsResponse
import retrofit2.Response

class DogsRepo(private val dogApi: DogApi) {
    suspend fun getBreedsList(): Response<DogsResponse> = dogApi.getBreedsList()

    suspend fun getBreedRandomImage(breed: String): Response<DogsImageResponse> =
        dogApi.getBreedRandomImage(breed)
}
