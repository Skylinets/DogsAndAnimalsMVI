package com.example.mvianimalscompose.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mascotas.dogs.MainState
import com.mascotas.dogs.data.AnimalRepo
import com.mascotas.dogs.data.DogsRepo
import com.mascotas.dogs.data.model.Dog
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: AnimalRepo,
    private val dogsRepo: DogsRepo
): ViewModel() {

    val userIntent = Channel<MainIntent> (Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        handleIntent()
        sendIntent(MainIntent.FetchAnimals)
    }

    fun sendIntent(intent: MainIntent) {
        viewModelScope.launch {
            userIntent.send(intent)
        }
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is MainIntent.FetchDogs -> fetchDogs()
                    is MainIntent.FetchAnimals -> fetchAnimals()
                    else -> {}
                }
            }
        }
    }

    private fun fetchAnimals(){
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val animals = repo.getAnimals()
                _state.value = MainState.Animals(animals)
            } catch (e: Exception) {
                _state.value = MainState.Error(e.localizedMessage ?: "Error fetching animals")
            }
        }
    }

    private fun fetchDogs(){
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val breedListResponse = dogsRepo.getBreedsList()
                if (breedListResponse.body()?.status == "success") {
                    val breeds = breedListResponse.body()?.images ?: emptyList()
                    val dogs = mutableListOf<Dog>()
                    breeds.forEach { breed ->
                        val imageResponse = dogsRepo.getBreedRandomImage(breed)
                        if (imageResponse.isSuccessful) {
                            dogs.add(Dog(name = breed, image = imageResponse.body()?.image ?: ""))
                        }
                    }
                    _state.value = MainState.Dogs(dogs)
                } else {
                    _state.value = MainState.Error("Error fetching dog breeds")
                }
            } catch (e: Exception) {
                _state.value = MainState.Error(e.localizedMessage ?: "Error fetching dogs")
            }
        }
    }

}
