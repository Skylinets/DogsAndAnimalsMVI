package com.mascotas.dogs.data.retrofit

import com.mascotas.dogs.data.api.AnimalApi
import com.mascotas.dogs.data.api.DogApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimalService {
    const val BASE_URL_ANIMAL = "https://raw.githubusercontent.com/CatalinStefan/animalApi/master/"
    const val BASE_URL_DOGS = "https://dog.ceo/api/"
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL_ANIMAL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: AnimalApi = getRetrofit().create(AnimalApi::class.java)


    private fun getRetrofitDog() = Retrofit.Builder()
        .baseUrl(BASE_URL_DOGS)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiDog: DogApi = getRetrofitDog().create(DogApi::class.java)
}