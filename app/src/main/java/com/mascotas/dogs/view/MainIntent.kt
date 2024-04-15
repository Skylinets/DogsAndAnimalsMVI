package com.example.mvianimalscompose.view

sealed class MainIntent {
    object FetchAnimals: MainIntent()
    object FetchDogs : MainIntent()
}