package com.mascotas.dogs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AnimalService {
    const val BASE_URL_ANIMAL = "https://raw.githubusercontent.com/CatalinStefan/animalApi/master/"
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL_ANIMAL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: AnimalApi = getRetrofit().create(AnimalApi::class.java)
}