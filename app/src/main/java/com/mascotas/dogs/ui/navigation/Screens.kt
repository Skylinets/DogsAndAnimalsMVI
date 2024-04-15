package com.mascotas.dogs.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mvianimalscompose.view.MainViewModel
import com.mascotas.dogs.ui.MainScreen
import com.mascotas.dogs.ui.MainScreenDog

@Composable
fun Home(vm: MainViewModel){
    MainScreen(vm = vm)

}
@Composable
fun Person(vm: MainViewModel){
    Column(Modifier.background(color = Color.White)) {
        MainScreenDog(vm = vm)

    }
}
@Composable
fun Favorite(){
    Column(Modifier.background(color = Color.Yellow)) {
        Text(text = "Estas en Favorite")
    }
}