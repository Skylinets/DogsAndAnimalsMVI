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
fun Home(vm: MainViewModel){
    MainScreen(vm = vm)

}
@Composable
fun Person(vm: MainViewModel){
    Column(Modifier.background(color = Color.Green)) {
       Text(text = "Person Aqui")

    }
}
@Composable
fun Favorite(){
    Column(Modifier.background(color = Color.Yellow)) {
        Text(text = "Estas en Favorite")
    }
}