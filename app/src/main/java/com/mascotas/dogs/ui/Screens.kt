package com.mascotas.dogs.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.mvianimalscompose.view.MainViewModel
import com.mascotas.dogs.MainState
import com.mascotas.dogs.model.Animal
import kotlinx.coroutines.flow.consumeAsFlow

@Composable
fun Home(vm: MainViewModel,onBottomClick: () -> Unit){


    Column(Modifier.fillMaxSize()) {
       // Text(text = "Estas en Home")
       // IdleScreen(onBottomClick)
        /*val animal: Animal
        AnimalsList(animals = animal)*/


        val state = vm.state.value

        when(state){
            is MainState.idle -> IdleScreen(onBottomClick)
            is MainState.Loading -> LoadingScreen()
            is MainState.Animals -> AnimalsList(animals = state.animals)
            is MainState.Error -> {
                IdleScreen(onBottomClick)
                Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }


    }
}
@Composable
fun Person(){
    Column(Modifier.background(color = Color.Green)) {
        Text(text = "Estas en Person")
    }
}
@Composable
fun Favorite(){
    Column(Modifier.background(color = Color.Yellow)) {
        Text(text = "Estas en Favorite")
    }
}