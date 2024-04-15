package com.mascotas.dogs.data.response

import com.google.gson.annotations.SerializedName

data class DogsImageResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var image: String
)