package com.example.mvianimalscompose.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mascotas.dogs.MainState
import com.mascotas.dogs.api.AnimalRepo
import com.mascotas.dogs.api.DogApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repo: AnimalRepo): ViewModel() {

    val userIntent = Channel<MainIntent> (Channel.UNLIMITED)
    var state = mutableStateOf<MainState>(MainState.Loading)
            private set

    init {
        fetchAnimals()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect(){ collector->
                when(collector){
                    is MainIntent.FetchAnimals -> fetchAnimals()
                }
            }
        }
    }

    private fun fetchAnimals(){
        viewModelScope.launch {
            state.value = MainState.Loading
            state.value = try {
                MainState.Animals(repo.getAnimals())
            } catch (e: Exception){
                MainState.Error(e.localizedMessage)
            }
            /*state.value = try {
               // MainState.Dogs(apiDog.getDogs())
            }catch (e: Exception){
                MainState.Error(e.localizedMessage)
            }*/
        }
    }

 /*   private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = AnimalService.apiDog.getDogsByBreeds("$query/images")
            val puppies = call.body()
            runOnUiThread{
                if(call.isSuccessful){
                    //show recyclerviews
                    val images: List<String> = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }

        }
    }*/
}