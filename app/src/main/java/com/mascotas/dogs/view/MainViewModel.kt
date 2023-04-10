package com.example.mvianimalscompose.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mascotas.dogs.MainState
import com.mascotas.dogs.api.AnimalRepo
import com.mascotas.dogs.model.Animal
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repo: AnimalRepo): ViewModel() {

    val userIntent = Channel<MainIntent> (Channel.UNLIMITED)
    var state = mutableStateOf<MainState>(MainState.Loading)
            private set
    val query  = mutableStateOf("")

    private var _list = MutableLiveData<List<Animal>>()
    val list: LiveData<List<Animal>> = _list
       // get() = _list

    init {
        fetchAnimals()
    }

    suspend fun performQuery(onQueryChanged: String) {
        val filterList = repo.getAnimals().filter { animal ->
            animal.name.lowercase().contains(onQueryChanged.lowercase())
        }
        _list.value = if (onQueryChanged.isEmpty()) repo.getAnimals() else filterList

    }


    fun newSearch(query: String){
        viewModelScope.launch {
           //repo.getAnimals().equals(query)
            repo.getAnimals().filter { this.equals(query) }
           // _list.equals(query)

        }
    }
    fun onQueryChanged(query: String){
        this.query.value = query
    }
    private fun fetchAnimals(){
        viewModelScope.launch {
            state.value = MainState.Loading
            state.value = try {
                MainState.Animals(repo.getAnimals())
            } catch (e: Exception){
                MainState.Error(e.localizedMessage)
            }

        }
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
}