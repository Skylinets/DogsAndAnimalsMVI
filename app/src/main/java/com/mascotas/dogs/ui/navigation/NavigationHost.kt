package com.mascotas.dogs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvianimalscompose.view.MainViewModel

@Composable
fun NavigationHost(navController: NavHostController, vm: MainViewModel) {
    NavHost(navController = navController, startDestination = Pantalla2.route) {
        composable(Pantalla1.route) {
            Home(vm)
        }
        composable(Pantalla2.route) {
            Person(vm)
        }
        composable(Pantalla3.route) {
            Favorite()
        }
    }
}